package com.boutique.util;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import com.boutique.common.util.DateUtil;
import com.boutique.common.util.Util;
import com.boutique.model.Cashier;
import com.boutique.model.Invoice;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	public static Boolean generateIntegerationFile(List<Invoice> invoices, String outputFileDir, String invoiceType, Cashier cashierObj) {

		try {
			Document document = new Document(PageSize.A4, 20, 20, 50, 50);

			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(outputFileDir + invoiceType + "-" + DateUtil.getCurrentTimestamp() + ".pdf"));

			document.open();

			if (Constants.INVOICE_TYPE_B2B.equals(invoiceType)
					|| Constants.INVOICE_TYPE_DISTRIBUTOR.equals(invoiceType)) {

				PdfPTable table = new PdfPTable(7);

				// Headers
				PdfPCell invoiceNumber = new PdfPCell(new Phrase("Invoice No."));
				PdfPCell clientName = new PdfPCell(new Phrase("Client Name"));
				PdfPCell date = new PdfPCell(new Phrase("Date"));
				PdfPCell branch = new PdfPCell(new Phrase("Branch"));
				PdfPCell currency = new PdfPCell(new Phrase("Currency"));
				PdfPCell total = new PdfPCell(new Phrase("Total"));
				PdfPCell cashierName = new PdfPCell(new Phrase("Cashier Name"));

				table.addCell(invoiceNumber);
				table.addCell(clientName);
				table.addCell(date);
				table.addCell(branch);
				table.addCell(currency);
				table.addCell(total);
				table.addCell(cashierName);

				if (invoices != null) {
					for (Invoice invoice : invoices) {

						table.addCell(invoice.getId() != null ? invoice.getId().toString() : "");
						table.addCell(invoice.getClientName() != null ? invoice.getClientName() : "");
						table.addCell(invoice.getPaymentDateTime() != null
								? DateUtil.formatDateOnly(invoice.getPaymentDateTime()) : "");
						table.addCell((invoice.getCashier() != null && invoice.getCashier().getStore() != null
								&& invoice.getCashier().getStore().getName() != null)
										? invoice.getCashier().getStore().getName() : "");
						table.addCell(invoice.getCurrency() != null ? invoice.getCurrency() : "");
						table.addCell(invoice.getAmount() != null ? invoice.getAmount().toString() : "");
						table.addCell((invoice.getCashier() != null && invoice.getCashier().getName() != null)
								? invoice.getCashier().getName() : "");

					}
				}

				document.add(table);

			} else if (Constants.INVOICE_TYPE_CUSTOMER.equals(invoiceType)) {

				PdfPTable table = new PdfPTable(5);

				// Headers
				PdfPCell branchName = new PdfPCell(new Phrase("Branch Name"));
				PdfPCell cashierName = new PdfPCell(new Phrase("Cashier Name"));
				PdfPCell currency = new PdfPCell(new Phrase("Currency"));
				PdfPCell total = new PdfPCell(new Phrase("Total"));
				PdfPCell date = new PdfPCell(new Phrase("Date"));

				table.addCell(branchName);
				table.addCell(cashierName);
				table.addCell(currency);
				table.addCell(total);
				table.addCell(date);

				Double usdTotal = 0.0, cdfTotal = 0.0, euroTotal = 0.0;
				
				if (Util.isNotNullAndEmpty(invoices)) {

					usdTotal = invoices.stream().filter(i -> Constants.CURRENCY_USD.equals(i.getCurrency()))
							.mapToDouble(i -> i.getAmount()).sum();
					cdfTotal = invoices.stream().filter(i -> Constants.CURRENCY_CDF.equals(i.getCurrency()))
							.mapToDouble(i -> i.getAmount()).sum();
					euroTotal = invoices.stream().filter(i -> Constants.CURRENCY_EURO.equals(i.getCurrency()))
							.mapToDouble(i -> i.getAmount()).sum();

				}
				
				String branch = (cashierObj != null && cashierObj.getStore() != null
						&& cashierObj.getStore().getName() != null)
								? cashierObj.getStore().getName() : "";
				String cashier = (cashierObj != null && cashierObj.getName() != null)
						? cashierObj.getName() : "";

				table.addCell(branch);
				table.addCell(cashier);
				table.addCell(Constants.CURRENCY_USD);
				table.addCell(usdTotal != null ? usdTotal.toString() : "");
				table.addCell(DateUtil.getDateOnlyAsString(new Date()));
				
				table.addCell(branch);
				table.addCell(cashier);
				table.addCell(Constants.CURRENCY_EURO);
				table.addCell(euroTotal != null ? euroTotal.toString() : "");
				table.addCell(DateUtil.getDateOnlyAsString(new Date()));
					
				table.addCell(branch);
				table.addCell(cashier);
				table.addCell(Constants.CURRENCY_CDF);
				table.addCell(cdfTotal != null ? cdfTotal.toString() : "");
				table.addCell(DateUtil.getDateOnlyAsString(new Date()));
				
				document.add(table);

			}

			document.close();

		} catch (Exception e) {
			System.err.println("Unable to generateDistributorAndB2BIntegerationFile");

			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

}
