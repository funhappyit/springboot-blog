package org.zerock.model;

import javax.annotation.Generated;

import lombok.Data;

@Data
public class KakaoProfile {

	public Integer id;
	public String connectedAt;
	public Properties properties;
	public KakaoAccount kakaoAccount;

	@Data
	class Properties {
		public String nickname;
	}

	@Data
	class KakaoAccount {
		public Boolean profileNicknameNeedsAgreement;
		public Profile profile;
		public Boolean hasEmail;
		public Boolean emailNeedsAgreement;
		public Boolean isEmailValid;
		public Boolean isEmailVerified;
		public String email;

		@Data
		class Profile {
			public String nickname;
		}
	}

}
