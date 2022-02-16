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
	public Map<String, Object> getBlogInfo(String userId, int crtPage, int postNo) {
		
		///////////////////////
		// 리스트 가져오기
		///////////////////////
		// 페이지 당 글 개수
		int listCnt = 10;
		
		// 시작 글 번호
		int startNum = (crtPage - 1) * listCnt + 1;
		
		// 마지막 글 번호
		int endNum = startNum + listCnt - 1;
		
		List<PostVo> postList = postDao.getPostList(userId, startNum, endNum);	/* 포스트 항목 */
		
		///////////////////////
		// 페이징
		///////////////////////
		int totalListCnt = postDao.totalCnt(userId);
		
		// 페이지 당 버튼 개수
		int pageBtnCnt = 5;
		
		// 마지막 버튼 번호
		int endBtnNo = (int) (Math.ceil(crtPage / (double)pageBtnCnt)) * pageBtnCnt;
		
		// 시작 버튼 번호
		int startBtnNo = endBtnNo - (pageBtnCnt - 1);
		
		// 다음 화살표 유무
		boolean next = false;
		if(endBtnNo * listCnt < totalListCnt) { // 마지막 페이지가 아닌 경우
			next = true;
		} else { 				// 마지막 페이지의 경우, 다음 화살표가 안보이면 마지막 버튼 값을 다시 계싼
			endBtnNo = (int) Math.ceil(totalListCnt / (double) listCnt);
		}
		
		// 이전 화살표 유무
		boolean prev = false;
		if(startBtnNo != 1) {
			prev = true;
		}
		
		List<CateVo> cateList = cateDao.getCate(userId);		/* 카테고리 항목 */
		BlogVo blogVo =  blogDao.getBlog(userId);				/* 블로그 정보 */
		
		PostVo postVo;
		if(postNo == 0) {
			postVo = postDao.getRecentPost(userId);			/* 가장 최신 포스트 */
		} else {
			postVo = postDao.getPostVo(postNo);
		}
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cateList", cateList);
		map.put("blogVo", blogVo);
		map.put("postList", postList);
		map.put("postVo", postVo);
		
		map.put("startBtnNo", startBtnNo);
		map.put("endBtnNo", endBtnNo);
		map.put("prev", prev);
		map.put("next", next);

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
