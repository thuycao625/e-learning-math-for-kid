package com.codeenginestudio.elearning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.constant.Constant;
import com.codeenginestudio.elearning.dao.AssessmentDAO;
import com.codeenginestudio.elearning.dao.ClassDAO;
import com.codeenginestudio.elearning.dao.entity.AssessmentEntity;
import com.codeenginestudio.elearning.dto.AssessmentDTO;
import com.codeenginestudio.elearning.service.AssessmentService;
import com.codeenginestudio.elearning.util.AssessmentUtil;
import com.codeenginestudio.elearning.util.CommonUtil;

@Service("assessmentService")
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	private AssessmentDAO assessmentDAO;

	@Autowired
	private ClassDAO classDAO;

	@Override
	public List<AssessmentDTO> getListAssessment() {
		List<AssessmentEntity> listAssessment = (List<AssessmentEntity>) assessmentDAO.findAll();

		List<AssessmentDTO> assessmentDTO = new ArrayList<>();
		for (AssessmentEntity assessment : listAssessment) {
			assessmentDTO.add(AssessmentUtil.parseToDTO(assessment));
		}
		return assessmentDTO;
	}

	@Override
	public Page<AssessmentDTO> getPageListAssessment(Integer page) {
		Pageable pageable = (Pageable) PageRequest.of(CommonUtil.getInt(page), Constant.ITEM_PER_PAGE);

		Page<AssessmentEntity> listAssessment = assessmentDAO.findAll(pageable);

		return listAssessment.map(x -> (AssessmentUtil.parseToDTO(x)));

	}

	@Override
	public void saveAssessment(AssessmentDTO assessmentDTO) {

		AssessmentEntity assessmentEntity = new AssessmentEntity();
		assessmentEntity.setAssessmentname(assessmentDTO.getAssessmentname());
		assessmentEntity.setClassForeign(classDAO.getClassByClassid(assessmentDTO.getClassForeign().getClassid()));
		assessmentEntity.setExpireddate(assessmentDTO.getExpireddate());
		assessmentEntity.setStartdate(assessmentDTO.getStartdate());
		assessmentEntity.setStatus(assessmentDTO.getStatus());

		assessmentDAO.saveAndFlush(assessmentEntity);
	}

	@Override
	public void deleteById(Long assessmentid) {
		assessmentDAO.deleteById(assessmentid);

	}

	@Override
	public AssessmentDTO getAssessmentByAssessmentid(Long assessmentid) {
		return AssessmentUtil.parseToDTO(assessmentDAO.getOne(assessmentid));
	}

	@Override
	public void editAssessmentStatus(Long assessmentid) {
		AssessmentEntity assessmentEntity = assessmentDAO.getOne(assessmentid);
		assessmentEntity.setStatus(!assessmentEntity.getStatus());
		assessmentDAO.saveAndFlush(assessmentEntity);

	}

	@Override
	public AssessmentDTO findByAssessmentName(String assessmentname) {
		List<AssessmentDTO> listAssessment = getListAssessment();

		for (AssessmentDTO existed : listAssessment) {
			if (assessmentname.equals(existed.getAssessmentname())) {
				return existed;
			}
		}
		return null;
	}

	@Override
	public List<AssessmentDTO> getListAssessmentByUnExpired() {

		LocalDate currentDate = LocalDate.now();
		List<AssessmentDTO> listAssessment = getListAssessment();
		List<AssessmentDTO> listAssessmentUnExpired = new ArrayList<>();

		for (AssessmentDTO assessment : listAssessment) {
			if (!assessment.getExpireddate().isBefore(currentDate) || assessment.getExpireddate().equals(currentDate)) {
				listAssessmentUnExpired.add(assessment);
			}
		}
		return listAssessmentUnExpired;
	}

	@Override
	public List<AssessmentDTO> getListAssessmentByExpired() {

		LocalDate currentDate = LocalDate.now();
		List<AssessmentDTO> listAssessment = getListAssessment();
		List<AssessmentDTO> listAssessmentExpired = new ArrayList<>();

		for (AssessmentDTO assessment : listAssessment) {
			if (assessment.getExpireddate().isBefore(currentDate)) {
				listAssessmentExpired.add(assessment);
			}
		}
		return listAssessmentExpired;
	}

}
