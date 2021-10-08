package com.bridgelabz.api.service;

import com.bridgelabz.api.dto.LabelDTO;
import com.bridgelabz.api.util.ResponseDTO;

public interface ILabelService {
	
	ResponseDTO addLabel(String userIdToken, LabelDTO labelDTO);
	
	ResponseDTO updateLabel(String userIdToken, int labelId, LabelDTO labelDTO);

	ResponseDTO getAllLabelsByUser(String UserIdToken);
	
	ResponseDTO deleteLabel(String userIdToken, int labelId);
	
	ResponseDTO removeAllNotesByLabel(String userIdToken, int labelId);
	
	ResponseDTO deleteAllLabelByUser(String userIdToken);
}
