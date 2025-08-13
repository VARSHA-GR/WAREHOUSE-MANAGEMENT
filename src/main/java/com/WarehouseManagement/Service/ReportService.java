package com.WarehouseManagement.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WarehouseManagement.Model.Report;
import com.WarehouseManagement.Repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // Generate a new report
    public Report generateReport(String reportName, String reportType) {
        Report report = new Report();
        report.setReportName(reportName);
        report.setReportType(reportType);
        report.setGeneratedAt(LocalDateTime.now());

        // Simulate report content generation
        String content = "Performance Report for " + reportType + " generated at " + report.getGeneratedAt();
        report.setReportContent(content);

        return reportRepository.save(report);
    }

    // Download report by ID
    public Report downloadReport(Integer reportId) {
        return reportRepository.findById(reportId).orElseThrow(() ->
            new RuntimeException("Report ID not found: " + reportId)
        );
    }
}

