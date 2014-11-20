package Parser;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dokonaly
 *
 */
public class Infobox_country implements Serializable{
	
	private String title;
	private String common_name;
	private String image_flag;
	private String image_coat;
	private String capital;
	private String[] official_religion;
	private String[] official_languages;
	private String[] government_type;
	private String area_km2;
	private String area_sq_mi;
	private String population_estimate;
	private String population_estimate_rank;
	private String currency;
	private String currency_code;


	/**
	 * @return the official_religion
	 */
	public String[] getOfficial_religion() {
		return official_religion;
	}

	/**
	 * @param official_religion the official_religion to set
	 */
	public void setOfficial_religion(String[] official_religion) {
		this.official_religion = official_religion;
	}

	/**
	 * @return the official_languages
	 */
	public String[] getOfficial_languages() {
		return official_languages;
	}

	/**
	 * @param official_languages the official_languages to set
	 */
	public void setOfficial_languages(String[] official_languages) {
		this.official_languages = official_languages;
	}

	/**
	 * @return the government_type
	 */
	public String[] getGovernment_type() {
		return government_type;
	}

	/**
	 * @param government_type the government_type to set
	 */
	public void setGovernment_type(String[] government_type) {
		this.government_type = government_type;
	}

	/**
	 * @return the area_km2
	 */
	public String getArea_km2() {
		return area_km2;
	}

	/**
	 * @param area_km2 the area_km2 to set
	 */
	public void setArea_km2(String area_km2) {
		this.area_km2 = area_km2;
	}

	/**
	 * @return the area_sq_mi
	 */
	public String getArea_sq_mi() {
		return area_sq_mi;
	}

	/**
	 * @param area_sq_mi the area_sq_mi to set
	 */
	public void setArea_sq_mi(String area_sq_mi) {
		this.area_sq_mi = area_sq_mi;
	}

	/**
	 * @return the population_estimate
	 */
	public String getPopulation_estimate() {
		return population_estimate;
	}

	/**
	 * @param population_estimate the population_estimate to set
	 */
	public void setPopulation_estimate(String population_estimate) {
		this.population_estimate = population_estimate;
	}

	/**
	 * @return the population_estimate_rank
	 */
	public String getPopulation_estimate_rank() {
		return population_estimate_rank;
	}

	/**
	 * @param population_estimate_rank the population_estimate_rank to set
	 */
	public void setPopulation_estimate_rank(String population_estimate_rank) {
		this.population_estimate_rank = population_estimate_rank;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the currency_code
	 */
	public String getCurrency_code() {
		return currency_code;
	}

	/**
	 * @param currency_code the currency_code to set
	 */
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	
	

	/**
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * @return the image_coat
	 */
	public String getImage_coat() {
		return image_coat;
	}

	/**
	 * @param image_coat the image_coat to set
	 */
	public void setImage_coat(String image_coat) {
		this.image_coat = image_coat;
	}

	/**
	 * @return the image_flag
	 */
	public String getImage_flag() {
		return image_flag;
	}

	/**
	 * @param image_flag the image_flag to set
	 */
	public void setImage_flag(String image_flag) {
		this.image_flag = image_flag;
	}

	/**
	 * @return the common_name
	 */
	public String getCommon_name() {
		return common_name;
	}

	/**
	 * @param common_name the common_name to set
	 */
	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}

	public String getTitle() {
	       return title;
	 }
	
	public void setTitle(String a) {
	        this.title = a;
	 }

	
}
