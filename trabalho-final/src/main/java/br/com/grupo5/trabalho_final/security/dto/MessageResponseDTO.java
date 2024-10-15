package br.com.grupo5.trabalho_final.security.dto;

public class MessageResponseDTO {

	
		private String message;
		
		
		public MessageResponseDTO(String message) {
			this.message = message;
		}

	
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
