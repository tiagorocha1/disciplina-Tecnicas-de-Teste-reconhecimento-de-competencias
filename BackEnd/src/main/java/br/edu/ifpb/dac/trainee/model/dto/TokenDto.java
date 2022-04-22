package br.edu.ifpb.dac.trainee.model.dto;

public class TokenDto {

	private String user;
	private boolean validToken;
	
	public TokenDto(String user, boolean validToken) {
		super();
		this.user = user;
		this.validToken = validToken;
	}
	public String getUser() {
		return user;
	}
	public boolean isValidToken() {
		return validToken;
	}
	

}
