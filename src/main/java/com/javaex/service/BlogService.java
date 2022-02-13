package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	@Autowired
	CategoryDao cateDao;
	@Autowired
	PostDao postDao;
	
	/* 블로그 첫 화면 정보들 */
	public Map<String, Object> getBlogInfo(String userId, int crtPage) {
		
		///////////////////////
		// 리스트 가져오기
		///////////////////////
		int listCnt = 10;
		int endNum = (crtPage - 1) * listCnt + 1;
		int startNum = endNum + listCnt - 1;
		
		///////////////////////
		// 페이징
		///////////////////////
		int totalListCnt = postDao.totalCnt(userId);
		int pageBtnCnt = 5;
		int endBtnNo = (int) (Math.ceil(crtPage / (double)pageBtnCnt)) * pageBtnCnt;
		int startBtnNo = endBtnNo - (pageBtnCnt - 1);
		
		// ************** 앞 뒤 화살표 유무 *****************
		
		
		List<CateVo> cateList = cateDao.getCate(userId);		/* 카테고리 항목 */
		BlogVo blogVo =  blogDao.getBlog(userId);				/* 블로그 정보 */
		List<PostVo> postList = postDao.getPostList(userId, startNum, endNum);	/* 포스트 항목 */
		PostVo postVo = postDao.getRecentPost(userId);			/* 가장 최신 포스트 */

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cateList", cateList);
		map.put("blogVo", blogVo);
		map.put("postList", postList);
		map.put("postVo", postVo);
		map.put("startBtnNo", startBtnNo);
		map.put("endBtnNo", endBtnNo);

		return map;
	}
	
	/* 블로그 사진, 제목 수정하기 */
	public void modifyBlog(Map<String, Object> map) {
		System.out.println("BlogService.modifyBlog()");
		
		MultipartFile file = (MultipartFile) map.get("file");
		BlogVo bvo = (BlogVo) map.get("bvo");

		String saveName;
		String saveDir = "C:\\javaStudy\\file\\upload";
		String filePath;
		String orgName = file.getOriginalFilename();
		
		if(orgName == "") { // 기존 파일 정보를 가져오기
			saveName = blogDao.getLogoFile(bvo.getId());
			filePath = saveDir + "\\" + saveName;

		} else {			
			String exName = orgName.substring(orgName.lastIndexOf("."));
			saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			filePath = saveDir + "\\" + saveName;
		}
		
		bvo.setLogoFile(saveName);
		
		/* db에 저장 */
		blogDao.modifyBlog(bvo);
		
		/* 파일 저장 */
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/* 글쓰기 화면에서 카테고리 목록 가져오기 */
	public List<CateVo> writeForm(String userId){
		return cateDao.getCate(userId);
	}
	
	/* 글 저장 */
	public void addPost(PostVo pvo) {
		postDao.addPost(pvo);
	}
	
	/* 블로그 수정 화면에 기존 정보 불러오기 */
	public BlogVo getBlogVo(String userId) {
		return blogDao.getBlog(userId);
	}
}
