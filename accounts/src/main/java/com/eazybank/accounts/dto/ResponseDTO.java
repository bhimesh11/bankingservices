package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Response", description = "Schema to hold successful response information")
public class ResponseDTO {
	@Schema(description = "Status code in the response")
	private String statuscode;
	@Schema(description = "Status message in the response")
	private String statusMsg;

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	@Override
	public String toString() {
		return "ResponseDTO [statuscode=" + statuscode + ", statusMsg=" + statusMsg + "]";
	}

	public ResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseDTO(String statuscode, String statusMsg) {
		super();
		this.statuscode = statuscode;
		this.statusMsg = statusMsg;
	}

}
