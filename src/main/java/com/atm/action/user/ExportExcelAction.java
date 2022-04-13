package com.atm.action.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.TransactionExtended;

public class ExportExcelAction extends Action {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sh = workbook.createSheet("Balance Enquiry");
		String[] headings = {"Type", "Date", "Time", "ID", "Name", "Creditor ID", "Creditor Name", "Amount"};
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setColor(IndexedColors.BLACK.index);
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		
		Row headerRow = sh.createRow(0);
		
		for (int i = 0; i < headings.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headings[i]);
			cell.setCellStyle(headerStyle);
		}
		
		// Fill data 
		HttpSession session = request.getSession();
		List<TransactionExtended> exportList = (List<TransactionExtended>) session.getAttribute("userExportList");
		int rowNum = 1;
		
		for (TransactionExtended trans : exportList) {
			Row row = sh.createRow(rowNum++);
			row.createCell(0).setCellValue(trans.getType().toString());
			row.createCell(1).setCellValue(trans.getDate().toString());
			row.createCell(2).setCellValue(trans.getTime().toString());
			row.createCell(3).setCellValue(trans.getAccId());
			row.createCell(4).setCellValue(trans.getName());
			row.createCell(5).setCellValue(trans.getCreditorId());
			row.createCell(6).setCellValue(trans.getCreditorName());
			row.createCell(7).setCellValue(trans.getAmount());
		}
		
		// Write to file
		String rootPath = request.getRealPath("/") + "excel";
		File folder = new File(rootPath);
		if (!folder.exists()) {
			if (!folder.mkdir()) {
				System.out.println("Can't create excel folder");
			}
		}
		
		File newFile = new File(folder.getPath() + "/export.xlsx");
		FileOutputStream fileOut = new FileOutputStream(newFile);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		
		// Setup for downloading the file
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
		URL url = getServlet().getServletContext().getResource("excel/export.xlsx");
		InputStream in = url.openStream();
		
		ServletOutputStream out = response.getOutputStream();
		
		byte[] outputByte = new byte[4096];
		while (in.read(outputByte, 0, 4096) != -1) {
			out.write(outputByte, 0, 4096);
		}
		in.close();
		out.flush();
		out.close();
		
		return null;
	}
}
