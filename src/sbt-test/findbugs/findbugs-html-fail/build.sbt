name := "findbugs-html-fail"

organization := "uk.co.josephearl"

version := "2.4.1"

findbugsReportType := Some(FindBugsReportType.Html)

findbugsReportPath := Some(target.value / "findbugs-report.html")

findbugsFailOnError := true
