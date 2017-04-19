package com.boutique.common.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boutique.common.dao.DaoManager;
import com.boutique.common.model.StatusMessage;
import com.boutique.common.util.DateUtil;
import com.boutique.common.util.Util;
import com.boutique.controller.CartBean;
import com.boutique.controller.ReportBean;
import com.boutique.controller.SessionBean;
import com.boutique.controller.admin.ProductBean;
import com.boutique.controller.admin.StockBean;
import com.boutique.controller.admin.StoreBean;
import com.boutique.dao.CashierDao;
import com.boutique.dao.InvoiceDao;
import com.boutique.dao.ProductDao;
import com.boutique.dao.StockDao;
import com.boutique.model.Product;
import com.boutique.model.ProductCategory;
import com.boutique.model.Store;
import com.boutique.util.DataUtil;
import com.boutique.validation.ErrorMessage;
import com.boutique.validation.ValidationErrors;

public abstract class AbstractController {

	protected ValidationErrors validationErrors = new ValidationErrors();
	protected ValidationErrors validationWarnings = new ValidationErrors();

	private Integer selectedProductCategoryId = -1;
	private Integer selectedProductId = -1;
	private Integer selectedStoreId = -1;
	private List<Product> products = new ArrayList<Product>();
	
	

	/**
	 * Default constructor.
	 */
	public AbstractController() {
		super();
	}

	/**
	 * This method returns validation errors.
	 * 
	 * @return Validation errors.
	 */
	public ValidationErrors getValidationErrors() {
		return validationErrors;
	}

	/**
	 * This method is used to set validation errors.
	 * 
	 * @param validationErrors
	 *            Specifies Validation errors object.
	 */
	public void setValidationErrors(ValidationErrors validationErrors) {
		this.validationErrors = validationErrors;
	}

	/**
	 * This method returns validation warnings.
	 * 
	 * @return Validation warnings.
	 */
	public ValidationErrors getValidationWarnings() {
		return validationWarnings;
	}

	/**
	 * This method returns controller object from faces context.
	 * 
	 * @param controllerName
	 *            Specifies name of controller object.
	 * @param clazz
	 *            Specifies class of controller object.
	 * @return Controller object from faces context.
	 */
	public Object getControllerObject(String controllerName, Class<?> clazz) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elcontext = facesContext.getELContext();
		ValueExpression ve = facesContext.getApplication().getExpressionFactory().createValueExpression(elcontext, "#{" + controllerName + "}", clazz);
		return ve.getValue(elcontext);
	}

	/**
	 * This method returns request parameter value.
	 * 
	 * @param parameterName
	 *            Specifies name of parameter.
	 * @return Request parameter value.
	 */
	public Object getRequestParameter(String parameterName) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameterName);
	}

	/**
	 * This method returns initial parameter map from faces context.
	 * 
	 * @return Initial parameter map from faces context.
	 */
	public Map<?, ?> getInitialParameterMap() {
		Map<?, ?> initParameterMap = FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap();
		return initParameterMap;
	}

	/**
	 * This method returns session map from faces context.
	 * 
	 * @return Session map from faces context.
	 */
	public Map<?, ?> getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	/**
	 * This method returns request map from faces context.
	 * 
	 * @return Request map from faces context.
	 */
	public Map<?, ?> getRequestMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
	}

	/**
	 * This method returns request parameter map from faces context.
	 * 
	 * @return Request parameter map from faces context.
	 */
	public Map<?, ?> getRequestParameterMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	/**
	 * This method returns user session object if it is already created.
	 * 
	 * @return User session object.
	 */
	public HttpSession getSession() {
		return getSession(false);
	}

	/**
	 * This method returns user session object. If create new flag is set then
	 * it creates new session if it is not created already.
	 * 
	 * @param createNewIfNotExit
	 *            Specifies whether to create a new session if it is not there
	 *            already.
	 * @return User session object.
	 */
	public HttpSession getSession(boolean createNewIfNotExit) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (HttpSession) facesContext.getExternalContext().getSession(createNewIfNotExit);
	}

	/**
	 * This method returns session id.
	 * 
	 * @return Session id.
	 */
	public String getSessionId() {
		HttpSession session = getSession();
		if (session == null) {
			return null;
		} else {
			return session.getId();
		}
	}

	/**
	 * This method returns external context.
	 * 
	 * @return External context.
	 */
	public ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public ServletContext getServletContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		return servletContext;
	}

	public HttpServletRequest getRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		return servletRequest;
	}

	public HttpServletResponse getResponse() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse servletResponse = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		return servletResponse;
	}

	/**
	 * This method concatenate two objects.
	 * 
	 * @param object1
	 *            Specifies object1.
	 * @param object2
	 *            Specifies object2.
	 * @param separtor
	 *            Specifies separator between two objects.
	 * @return Concatenated string of two objects.
	 */
	public String concat(Object object1, Object object2, String separtor) {
		return (object1 != null ? object1.toString() : "") + (separtor != null ? separtor : "") + (object2 != null ? object2.toString() : "");
	}

	/**
	 * This method returns size of list.
	 * 
	 * @param list
	 *            Specifies list of items.
	 * @return Size of list.
	 */
	@SuppressWarnings("rawtypes")
	public int getSize(List list) {
		if (list == null) {
			return 0;
		}

		return list.size();
	}

	public boolean contains(List<Integer> list, Integer id) {
		if (Util.isNotNullAndEmpty(list) && list.contains(id)) {
			return true;
		}

		return false;
	}

	/**
	 * This method formats date in general form (EEEE, MMMM dd, yyyy).
	 * 
	 * @param date
	 *            Specifies date object.
	 * @return Date in general form.
	 */
	public String getGeneralFormatedDate(Date date) {
		if (date == null) {
			return "";
		}

		return DateUtil.getGeneralFormatedDate(date);
	}

	/**
	 * This method formats date in general short form (EE, MMM dd, yyyy).
	 * 
	 * @param date
	 *            Specifies date object.
	 * @return Date in general short form.
	 */
	public String getGeneralShortFormatedDate(Date date) {
		if (date == null) {
			return "";
		}

		return DateUtil.getGeneralShortFormatedDate(date);
	}

	/**
	 * This method formats date time in general short form (EE, MMM dd, yyyy,
	 * hh:mm a).
	 * 
	 * @param date
	 *            Specifies date time object.
	 * @return Date time in general short form.
	 */
	public String getGeneralShortFormatedDateTime(Date date) {
		if (date == null) {
			return "";
		}

		return DateUtil.getGeneralShortFormatedDateTime(date);
	}

	/**
	 * This method formats date time in general short form without comma(EE MMM
	 * dd, yyyy HH:mm).
	 * 
	 * @param date
	 *            Specifies date time object.
	 * @return Date time in general short form without comma.
	 */
	public String getGeneralShortFormatedDateTimeNoComma(Date date) {
		if (date == null) {
			return "";
		}

		return DateUtil.getGeneralShortFormatedDateTimeNoComma(date);
	}

	/**
	 * This method checks whether user is permitted on given resource.
	 * 
	 * @param resourceName
	 *            Specifies resource name.
	 * @return True if user is permitted on given resource. Otherwise returns
	 *         false.
	 */
	@SuppressWarnings("unchecked")
	public Boolean isPermitted(String resourceName) {
		Map<String, Boolean> accessResources = (Map<String, Boolean>) getSession(false).getAttribute("accessResources");

		if (accessResources != null && accessResources.get(resourceName) != null) {
			return accessResources.get(resourceName);
		} else
			return false;
	}

	/**
	 * This method returns date only part from date time object (MMM dd, yyyy).
	 * 
	 * @param date
	 *            Specifies date time object.
	 * @return Date only part from date time object
	 */
	public String getDateOnlyAsString(Date date) {
		if (date != null) {
			return DateUtil.getDateOnlyAsString(date);
		}
		return "";
	}

	/**
	 * This method returns substring from the given string value.
	 * 
	 * @param input
	 *            Specifies input string value.
	 * @param length
	 *            Specifies the number of characters to be returned.
	 * @return Substring from the given string value.
	 */
	public String subString(String input, int length) {
		if (input == null) {
			return null;
		}

		if (input.length() > length)
			return input.substring(0, length);
		else
			return input;
	}

	/**
	 * This method returns substring from the given string value. Tailing dots
	 * are added at the end of string the actual string is larger than requested
	 * number of characters.
	 * 
	 * @param input
	 *            Specifies input string value.
	 * @param length
	 *            Specifies the number of characters to be returned.
	 * @return Substring from the given string value. Tailing dots are added at
	 *         the end of string the actual string is larger than requested
	 */
	public String subStringWithTailingDots(String input, int length) {
		if (input == null) {
			return null;
		}

		if (input.length() > length) {
			return input.substring(0, length) + "...";
		} else
			return input;
	}

	/**
	 * The method checks whether a given string is empty or null.
	 * 
	 * @param string
	 *            Specifies input string value.
	 * @return True if the given string value is either null or empty. Otherwise
	 *         returns false.
	 */
	public boolean isNotNullAndEmpty(String string) {
		return Util.isNotNullAndEmpty(string);
	}

	public void redirectToPage(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
		}
	}

	/**
	 * This method formats date time in general short form (MM/dd/yyyy).
	 * 
	 * @param date
	 *            Specifies date time object.
	 * @return Date time in general short form.
	 */
	public String getDateOnlyFormat(Date date) {
		if (date == null) {
			return "";
		}

		return DateUtil.formatDateOnly(date);
	}

	/**
	 * This method formats date time in general short form (MM/dd/yyyy HH:mm).
	 * 
	 * @param date
	 *            Specifies date time object.
	 * @return Date time in general short form.
	 */
	public String getDateAndTimeOnlyFormat(Date date) {
		if (date == null) {
			return "";
		}

		return DateUtil.formatDateByDateTime(date);
	}

	public String replaceSpecialCharacters(String inputString) {
		return inputString.replaceAll(".png", "").replaceAll("\\.", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "").replaceAll(",", "").replaceAll("'", "")
				.replaceAll("&", "");
	}

	/**
	 * This method returns date in US format, i.e. MM/dd/yyyy.
	 * 
	 * @param date
	 *            Specifies date.
	 * @return Date in US format, i.e. MM/dd/yyyy.
	 */
	public static String formatDateUS(Date date) {
		if (date == null) {
			return "";
		}
		return DateUtil.formatDateUS(date);
	}

	public String getTimeStamp() {
		return DateUtil.formatDate("yyyy-MM-dd HH-mm", new Date());
	}

	public String getClientBrowser(String userAgent, String user) {
		String browser = null;
		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/",
			// "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		return browser;
	}

	public String getClientOS(String userAgent) {
		String os = null;
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		return os;
	}

	/**
	 * This method returns real (physical path) of application.
	 * 
	 * @return Real (physical path) of application.
	 */
	public static String getRealPath() {
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String deploymentDirectoryPath = ctx.getRealPath("/");

		return deploymentDirectoryPath;
	}

	public boolean isAjaxRequest() {
		return FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest();
	}

	public boolean isPostBack() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public String getClientIPAddress() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ipAddress = request.getRemoteAddr();

		return ipAddress;
	}

	public String getClientUserAgent() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String userAgent = request.getHeader("user-agent");

		return userAgent;
	}

	/**
	 * getHeadersInfo method returns request Headers information.
	 * Host,User-Agent,Accept-Encoding etc
	 * 
	 * @return Map of request headers
	 */
	public Map<String, String> getHeadersInfo() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Map<String, String> map = new HashMap<String, String>();

		Enumeration<?> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		String userIpAddress = request.getRemoteAddr();
		map.put("IP-Address", userIpAddress);

		return map;
	}

	/**
	 * The addGRComValidationErrorsFromStatusMessage() method is used to add
	 * validation error messages in bean from StatusMessage
	 * 
	 * @param statusMessage
	 *            Specifies the StatusMessage instance
	 */
	public void addValidationErrorsFromStatusMessage(StatusMessage statusMessage, ValidationErrors validationErrors) {

		if (statusMessage != null && Util.isNotNullAndEmpty(statusMessage.getValidationErrors().getActionErrors())) {

			for (String key : statusMessage.getValidationErrors().getActionErrors().keySet()) {

				ErrorMessage errorMessage = statusMessage.getValidationErrors().getActionErrors().get(key);
				validationErrors.addError(key, errorMessage.getMessage());
			}
		}
	}

	public boolean checkDeviceIsIphone() {

		HttpServletRequest request = getRequest();

		String userAgent = request.getHeader("User-Agent");
		boolean indicator = false;

		if (Util.isNotNullAndEmpty(userAgent) && userAgent.indexOf("iphone") != -1) {

			indicator = true;
		}

		return indicator;
	}

	/**
	 * This method converts price to desired format which is '###,###.00' in our
	 * case.
	 * 
	 * @param price
	 *            price to be converted
	 * @return converted price in desired format
	 */
	public String convertToCustomFormat(Double price) {
		String value = price.toString();
		if (value != "") {
			DecimalFormat myFormatter = new DecimalFormat("###,###");
			return myFormatter.format(Double.parseDouble(value));
		}
		return value;
	}

	/**
	 * The getSessionBean() method is used to get SessionBean instance.
	 *
	 * @return SessionBean object
	 */
	protected SessionBean getSessionBean() {
		return (SessionBean) getControllerObject("sessionBean", SessionBean.class);
	}

	/**
	 * The getCartBean() method is used to get CartBean instance.
	 *
	 * @return CartBean object
	 */
	protected CartBean getCartBean() {
		return (CartBean) getControllerObject("cartBean", CartBean.class);
	}
	
	/**
	 * The getProductBean() method is used to get ProductBean instance.
	 *
	 * @return ProductBean object
	 */
	protected ProductBean getProductBean() {
		return (ProductBean) getControllerObject("productBean", ProductBean.class);
	}
	
	/**
	 * The getStoreBean() method is used to get StoreBean instance.
	 *
	 * @return StoreBean object
	 */
	protected StoreBean getStoreBean() {
		return (StoreBean) getControllerObject("storeBean", StoreBean.class);
	}
	
	/**
	 * The getStockBean() method is used to get StockBean instance.
	 *
	 * @return StockBean object
	 */
	protected StockBean getStockBean() {
		return (StockBean) getControllerObject("stockBean", StockBean.class);
	}

	/**
	 * The getReportBean() method is used to get ReportBean instance.
	 *
	 * @return ReportBean object
	 */
	protected ReportBean getReportBean() {
		return (ReportBean) getControllerObject("reportBean", ReportBean.class);
	}

	protected InvoiceDao getInvoiceDao() {
		return DaoManager.getInstance().getDao(InvoiceDao.class);
	}
	
	protected static ProductDao getProductDao() {
		return DaoManager.getInstance().getDao(ProductDao.class);
	}
	
	protected CashierDao getCashierDao() {
		return DaoManager.getInstance().getDao(CashierDao.class);
	}
	
	protected StockDao getStockDao() {
		return DaoManager.getInstance().getDao(StockDao.class);
	}

	public List<ProductCategory> getProductCategories() {
		return DataUtil.getProductCategories();
	}

	public List<Product> getAllProducts() {
		return DataUtil.getProducts();
	}
	
	public List<Store> getAllStores() {
		return DataUtil.getStores();
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}
	
	/**
	 * @return the selectedProductCategoryId
	 */
	public Integer getSelectedProductCategoryId() {
		return selectedProductCategoryId;
	}

	/**
	 * @param selectedProductCategoryId
	 *            the selectedProductCategoryId to set
	 */
	public void setSelectedProductCategoryId(Integer selectedProductCategoryId) {
		this.selectedProductCategoryId = selectedProductCategoryId;
	}

	/**
	 * @return the selectedProductId
	 */
	public Integer getSelectedProductId() {
		return selectedProductId;
	}

	/**
	 * @param selectedProductId
	 *            the selectedProductId to set
	 */
	public void setSelectedProductId(Integer selectedProductId) {
		this.selectedProductId = selectedProductId;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void loadProductsByCategory() {
		this.products = DataUtil.getProductsByCategoryId(this.selectedProductCategoryId);
	}

	/**
	 * @return the selectedStoreId
	 */
	public Integer getSelectedStoreId() {
		return selectedStoreId;
	}

	/**
	 * @param selectedStoreId the selectedStoreId to set
	 */
	public void setSelectedStoreId(Integer selectedStoreId) {
		this.selectedStoreId = selectedStoreId;
	}
	
	public Double formatDobuleTo2Decimal(Double price) {
		if (price != null) {
			DecimalFormat formatter = new DecimalFormat("##.00");
			price = Double.parseDouble(formatter.format(price));
		}
		return price;
	}
}
