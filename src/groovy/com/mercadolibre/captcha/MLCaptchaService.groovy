package com.mercadolibre.captcha

class MLCaptchaService {

	private String CAPTCHA_EXTENSION = "JPEG"
	private Integer DEFAULT_WIDTH = 140
	private Integer DEFAULT_HEIGHT = 85
	private String DEFAULT_SALT = "MercadoLibreCaptcha" 
	
	/**
	 * Generates a captcha image to challenge human beings on form submissions.
	 * @param captchaHash the hash to use to generate the image
	 * @param width the desired width
	 * @param height the desired height
	 * @return
	 */
	public ImgWordModel generateImage(captchaHash, Integer width, Integer height) {
		def text = getCaptchaTextForHash(captchaHash)
		
		ImgWordModel model = new ImgWordModel(
		  text,
		  CAPTCHA_EXTENSION,
		  (width?width.toInteger():DEFAULT_WIDTH),
		  (height?height.toInteger():DEFAULT_HEIGHT));
				
        model.generateImage();
		return model
	}

	
	/**
	 * Get a new captcha challenge phrase for a URL
	 * @return the captcha phrase is encrypted to be secure and encoded for a URL
	 */
	public String getNewChallenge() {
		return ImgWordUtil.encodeUrl(ImgWordUtil.generateWord() + "|" + DEFAULT_SALT, true)
	}
	
	/**
	 * Validates a given captchaChallenge hash against a user response
	 */
	public Boolean isValidAnswer(String captchaChallenge, String captchaResponse) {
		def dec = URLDecoder.decode(captchaChallenge, "UTF-8")
		return getCaptchaTextForHash(dec).equalsIgnoreCase(captchaResponse)
	}

	
	private String getCaptchaTextForHash(captchaHash) {
		String kwd = ImgWordUtil.decrypt(captchaHash);
		
		def tok = new StringTokenizer(kwd, "|");
		def text
		if (tok.hasMoreTokens()) {
			text = tok.nextToken()
		} else {
			text = kwd;
		}
		return text
	}
}
