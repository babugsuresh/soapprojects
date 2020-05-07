package hmrc.cds.dms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ListDataProcess {

	public static void main(String[] args) throws IOException {

		System.out.println("JESUS is MY LORD");

		File htmlTemplateFile = new File("src/resources/Report2.html");

		String htmlString = FileUtils.readFileToString(htmlTemplateFile, "UTF-8");

		String title = "New Page";

		String operation = "operation";
		String status = "  Up  ";
		String service = "service";
		String envName = "DIT";
		String response = "Down";
		String systemName = "TBD";

		List<OperationName> operationNames = new ArrayList<OperationName>();

		for (int i = 0; i < 2; i++) {
			OperationName operationName = new OperationName();
			operationName.setOperationName(operation + i);
			operationName.setStatus(status + i);
			operationName.setResponse(response + i);
			operationNames.add(operationName);
		}

		List<ServiceName> serviceNames = new ArrayList<ServiceName>();

		for (int i = 0; i < 1; i++) {
			ServiceName serviceName = new ServiceName();
			serviceName.setSystemName(systemName + i);
			serviceName.setServiceName(service + i);
			serviceName.setOperationNames(operationNames);
			serviceNames.add(serviceName);
		}

		List<ReportBean> rb = new ArrayList<ReportBean>();

		for (int i = 1; i <= 3; i++) {
			ReportBean reportBean = new ReportBean();
			reportBean.setEnvName(envName + i);
			reportBean.setServiceNames(serviceNames);
			rb.add(reportBean);
		}

		String body = "";

		String tag1 = "<section>  <h1>";
		String tag2 = "</h1><div class=\"tbl-header\">\r\n"
				+ "    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + "      <thead>\r\n"
				+ "        <tr><b>\r\n" + "          <th><b>System Name<b/></th>\r\n"
				+ "          <th><b>Service Name<b/></th>\r\n" + "          <th><b>Operation Name<b/></th>\r\n"
				+ "          <th><b>Status<b/></th>\r\n" + "          <th><b>Response XML<b/></th>\r\n"
				+ "        </tr>\r\n" + "      </thead>\r\n" + "    </table>\r\n" + "  </div>\r\n"
				+ "  <div class=\"tbl-content\">\r\n"
				+ "    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + "      <tbody>";

		String tag3 = "</tbody>\r\n" + "    </table>\r\n" + "  </div>\r\n" + "</section>";

		String tag4 = "<tr><td>";
		String tag5 = "</td></tr>";
		String tag6 = "</td><td>";
		String green = "<span class=\"label label-success\">";
		String red = "<span class=\"label label-danger\">";
		String blue = "<span class=\"label label-info\">";
		String tag7 = "</span>";

		String tag8 = "<button type=\"button\" class=\"collapsible\">";
		String tag9 = "</button>";
		String tag10 = "<div class=\"content\">";
		String tag11 = "</div>";
		
		//To Make response button to be clickable
		String js = "<script>\r\n" + 
				"var coll = document.getElementsByClassName(\"collapsible\");\r\n" + 
				"var i;\r\n" + 
				"\r\n" + 
				"for (i = 0; i < coll.length; i++) {\r\n" + 
				"  coll[i].addEventListener(\"click\", function() {\r\n" + 
				"    this.classList.toggle(\"active\");\r\n" + 
				"    var content = this.nextElementSibling;\r\n" + 
				"    if (content.style.display === \"block\") {\r\n" + 
				"      content.style.display = \"none\";\r\n" + 
				"    } else {\r\n" + 
				"      content.style.display = \"block\";\r\n" + 
				"    }\r\n" + 
				"  });\r\n" + 
				"}\r\n" + 
				"</script>";
		 
		
		//<button type="button" class="collapsible">Response</button>
		
		//tag8+Response+tage9
		
		//tag8+op.getResponse()+tag9
		
		String cc = "<div class=\"content\"><p>Lorem ipsum </p></div>";
		
		

		StringBuilder builder = new StringBuilder();

		for (ReportBean r : rb) {
			String toPublish = "";
			String dataToLoad = "";
			StringBuilder localBuilder = new StringBuilder();
			for (ServiceName sn : r.getServiceNames()) {
				for (OperationName op : sn.getOperationNames()) {
					dataToLoad = tag4 + sn.getSystemName() + tag6 + sn.getServiceName() + tag6 + op.getOperationName()
							+ tag6 + green + op.getStatus() + tag7 + tag6 +tag8+"Response"+"</button>"+cc+ tag5;
					localBuilder.append(dataToLoad);
				}
			}

			toPublish = localBuilder.toString();
			toPublish = tag1 + r.getEnvName() + tag2 + toPublish + tag3;
			builder.append(toPublish);
		}

		String finalDataToPublish = builder.toString();

		body = finalDataToPublish+js;

		htmlString = htmlString.replace("$title", title);
		htmlString = htmlString.replace("$body", body);
		File newHtmlFile = new File("src/resources/new.html");
		FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF-8");

	}

}
