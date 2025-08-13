package com.WarehouseManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.WarehouseManagement.Model.Report;
import com.WarehouseManagement.Service.ReportService;


@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/generate")
    public Report generateReport(@RequestParam String reportName, @RequestParam String reportType) {
        return reportService.generateReport(reportName, reportType);
    }

    @GetMapping("/download/{reportId}")
    public Report downloadReport(@PathVariable Integer reportId) {
        return reportService.downloadReport(reportId);
    }
}

