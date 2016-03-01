package nozama.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import nozama.service.ProductListServiceImpl;
import nozama.service.ProductPageServiceImpl;
import nozama.util.Util;

@Controller
public class CtrlProduct {

	@Autowired
	private ProductListServiceImpl PLS;
	
	@Autowired
	private ProductPageServiceImpl PPS;

	@RequestMapping(value = { "/liste-toutes-les-musiques", "/liste-toutes-les-musiques/{type}", "/liste-toutes-les-musiques/{support}/{recordType}/{years}/{type}", "/liste-toutes-les-musiques/{support}/{recordType}/{years}/{type}/{startResult}" }, method = RequestMethod.GET)
	public ModelAndView listAllMusic(HttpServletRequest request, @PathVariable("support") Optional<String> supportUrl, @PathVariable("recordType") Optional<String> recordTypeUrl, @PathVariable("years") Optional<String> yearsUrl, @PathVariable("type") Optional<String> typeUrl, @PathVariable("startResult") Optional<String> startResultUrl) {

		String support = PLS.getParametersString(supportUrl, "AllSupport");
		String recordType = PLS.getParametersString(recordTypeUrl, "AllType");
		String stringYears = PLS.getParametersString(yearsUrl, "default");
		String type = PLS.getParametersString(typeUrl, "ALL");
		String startResultString = PLS.getParametersString(startResultUrl, "1");

		int years = -1;
		if (Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}

		int startResult = 1;
		if (Util.convertToInt(startResultString)) {
			startResult = Integer.parseInt(startResultString);
		}

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PLS.getAllMusicsBySupport(support, recordType, years, type, startResult));
		product.put("recordType", recordType);
		product.put("support", support);
		product.put("years", years);
		product.put("type", type);
		product.put("numberPage", Math.ceil(((double) PLS.getCountAllMusicBySupport(support, recordType, years, type) / 12)));
		product.put("startPage", startResult);

		return new ModelAndView("listProduct/listProductMusic", product);
	}

	@RequestMapping(value = { "/liste-tous-les-films", "/liste-tous-les-films/{type}", "/liste-tous-les-films/{support}/{years}/{type}", "/liste-tous-les-films/{support}/{years}/{type}/{startResult}" }, method = RequestMethod.GET)
	public ModelAndView listAllMovies(HttpServletRequest request, @PathVariable("support") Optional<String> supportUrl, @PathVariable("type") Optional<String> typeUrl, @PathVariable("years") Optional<String> yearsUrl, @PathVariable("startResult") Optional<String> startResultUrl) {

		String support = PLS.getParametersString(supportUrl, "AllSupport");
		String type = PLS.getParametersString(typeUrl, "ALL");
		String startResultString = PLS.getParametersString(startResultUrl, "1");
		String stringYears = PLS.getParametersString(yearsUrl, "default");

		int startResult = 1;
		if (Util.convertToInt(startResultString)) {
			startResult = Integer.parseInt(startResultString);
		}

		int years = -1;
		if (Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PLS.getAllMovieBySupport(support, type, startResult, years));
		product.put("support", support);
		product.put("years", years);
		product.put("type", type);
		product.put("numberPage", Math.ceil(((double) PLS.getCountMovieBySupport(support, type, years) / 12)));
		product.put("startPage", startResult);

		return new ModelAndView("listProduct/listProductMovie", product);
	}

	@RequestMapping(value = { "/liste-tous-les-produits", "/liste-tous-les-produits/{years}", "/liste-tous-les-produits/{years}/{startResult}" }, method = RequestMethod.GET)
	public ModelAndView allProduct(HttpServletRequest request, @PathVariable("years") Optional<String> yearsUrl, @PathVariable("startResult") Optional<String> startResultUrl) {
		String stringYears = PLS.getParametersString(yearsUrl, "default");
		String startResultString = PLS.getParametersString(startResultUrl, "1");

		int startResult = 1;
		if (Util.convertToInt(startResultString)) {
			startResult = Integer.parseInt(startResultString);
		}

		int years = -1;
		if (Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}

		List<Map<String, Object>> allProduct = PLS.getAllProduct(years, startResult);

		if (((startResult - 1) * 12) > allProduct.size() - 1 || startResult <= 0) {
			startResult = 1;
		}

		Map<String, Object> product = new HashMap<String, Object>();
		int toIndexEndPagination = (startResult * 12) > allProduct.size() ? allProduct.size() : (startResult * 12);

		product.put("products", allProduct.subList(((startResult - 1) * 12), toIndexEndPagination));
		product.put("years", years);
		product.put("numberPage", Math.ceil((double) allProduct.size() / 12));
		product.put("startPage", startResult);

		return new ModelAndView("listProduct/listAll", product);
	}
	
	@RequestMapping(value = { "/product/{type}/{nameTagDateReleased}"}, method = RequestMethod.GET)
	public ModelAndView pageProduct(HttpServletRequest request, 
			@PathVariable("nameTagDateReleased") Optional<String> nameTagDateReleasedUrl,
			@PathVariable("type") Optional<String> typeUrl) {
		String nameTagDateReleased = PLS.getParametersString(nameTagDateReleasedUrl, "");
		String type = PLS.getParametersString(typeUrl, "");
		
		if(type.equals("")){
			return new ModelAndView("redirect:/");
		}

		if(nameTagDateReleased.equals("")){
			return new ModelAndView("redirect:/");
		}
		Map<String, Object> productItem = PPS.getProduct(nameTagDateReleased,type);
		
		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", productItem);

		if(type == "movie"){
			return new ModelAndView("pageProductMovie",product);
		}else{
			return new ModelAndView("pageProductMusic",product);
		}
	}
}
