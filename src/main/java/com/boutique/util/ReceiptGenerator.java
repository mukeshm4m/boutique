package com.boutique.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.boutique.common.util.DateUtil;
import com.boutique.model.Invoice;
import com.boutique.model.InvoiceProduct;

public class ReceiptGenerator {

	public static String generateReceipt(Invoice invoice, String path) {

		try {
			
			String emailTemplate = getTemplate(path);

			emailTemplate = emailTemplate.replaceAll("##INVOICE_NO##", invoice.getId().toString());
			
			emailTemplate = emailTemplate.replaceAll("##CLIENT_NAME_LABEL##", invoice.getClientNameByType());
			emailTemplate = emailTemplate.replaceAll("##CLIENT_NAME##", invoice.getClientName());
			
			emailTemplate = emailTemplate.replaceAll("##LOCATION##", invoice.getCashier().getStore().getName());
			
			emailTemplate = emailTemplate.replaceAll("##PAYMENT_DATE##", DateUtil.getGeneralShortFormatedDateTime(invoice.getPaymentDateTime()));
			
			emailTemplate = emailTemplate.replaceAll("##DEPOSITOR_NAME##", invoice.getDepositorName());
			
			emailTemplate = emailTemplate.replaceAll("##DEPOSIT_SLIP_NO##", invoice.getSlipNo());
			
			emailTemplate = emailTemplate.replaceAll("##TOTAL_AMOUNT##", invoice.getAmount().toString() + " " + invoice.getCurrency());
			
			emailTemplate = emailTemplate.replaceAll("##PAYMENT_REFERENCE_NO##", invoice.getPaymentReferenceNo());
			
			emailTemplate = emailTemplate.replaceAll("##INVOICE_PRODUCTS##", getInvoiceProductsHTML(invoice.getInvoiceProducts()));
			
			emailTemplate = emailTemplate.replaceAll("&", "&amp;");

			String receiptName = generatePdfFromHtml(emailTemplate, path);

			deleteOldReceipts(path);

			return receiptName;

		} catch (Exception e) {
			System.err.println(e);
		}

		return null;

	}

	private static String getTemplate(String path) throws FileNotFoundException, IOException {
		String templatePath = path + "WEB-INF/templates/receipt.xhtml";

		FileInputStream htmlFileInputStream = new FileInputStream(templatePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(htmlFileInputStream));

		String emailTemplate = "";
		String aLine = null;

		while ((aLine = br.readLine()) != null) {
			emailTemplate += aLine;
		}

		htmlFileInputStream.close();

		return emailTemplate;
	}

	/**
	 * The generatePdfFromHtml() method is used to generate pdf file from html
	 * 
	 * @param imageName
	 *            Specifies the image name
	 * @param memberId
	 *            Specifies the member Id
	 */
	private static String generatePdfFromHtml(String emailTemplate, String path) {
		try {

			ITextRenderer renderer = new ITextRenderer();

			renderer.setDocumentFromString(emailTemplate);
			renderer.layout();

			String outputFileDirectory = path + "receipts/";

			// Create intermediate directories if not exists
			File f = new File(outputFileDirectory);
			f.mkdirs();

			long dateMilis = new Date().getTime();

			String fileName = dateMilis + ".pdf";

			String outputFileName = outputFileDirectory + fileName;
			OutputStream os = new FileOutputStream(outputFileName);
			renderer.layout();
			renderer.createPDF(os);
			os.close();

			return fileName;

		} catch (Exception e) {
			System.err.println(e);
		}

		return null;
	}

	private static void deleteOldReceipts(String path) {

		try {

			File folder = new File(path + "receipts/");
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {

				if (listOfFiles[i].isFile()) {

					Date createdDate = new Date(listOfFiles[i].lastModified());

					if (DateUtil.getDaysBetween(new Date(), createdDate, true) >= 1) {
						listOfFiles[i].delete();
					}
				}
			}
		} catch (Exception se) {
			System.err.println(se);
		}
	}

	private static String getInvoiceProductsHTML(List<InvoiceProduct> invoiceProducts) {

		StringBuilder invoiceProductsHtml = new StringBuilder();

		for (InvoiceProduct invoiceProduct : invoiceProducts) {
			invoiceProductsHtml.append("<tr>");
			invoiceProductsHtml.append("<td style=\"padding: 5px 15px; padding-left: 5px; word-break: break-all;\">" + invoiceProduct.getProduct().getName() + "</td>");
			invoiceProductsHtml.append("<td style=\"padding: 5px 15px; word-break: break-all;\">" + invoiceProduct.getQuantity() + "</td>");
			invoiceProductsHtml.append("<td style=\"padding: 5px 15px; word-break: break-all;\">" + invoiceProduct.getProduct().getPrice() + "</td>");
			invoiceProductsHtml.append("<td style=\"padding: 5px 15px; word-break: break-all;\">" + invoiceProduct.getTotalPrice() + "</td>");

			invoiceProductsHtml.append("</tr>");
		}

		return invoiceProductsHtml.toString();
	}
}
