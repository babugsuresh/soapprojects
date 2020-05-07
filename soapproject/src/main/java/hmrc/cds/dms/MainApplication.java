package hmrc.cds.dms;

import com.ibm.wsdl.PortTypeImpl;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;


import javax.xml.soap.SOAPException;

public class MainApplication {

	private static final String BASEDIR = null;

	public static void main(String[] args) throws UnsupportedOperationException, SOAPException, IOException, WSDLException {
		System.out.println("------Hi Jesus-----");

		
		
		
		//String endPoint = "URL";
		
		
		
		
		File wsdlDir = new File(BASEDIR, "src/resources/wsdls/99 Adapters");
		
		System.out.println("\n----JESUS is my GOD----: "+wsdlDir.getName());

		String[] wsdlNames;
		wsdlNames = wsdlDir.list();

		File requestsDir = new File(BASEDIR, "src/resources/requests");

		String[] requestsnames;
		requestsnames = requestsDir.list();

		for (String requestsname : requestsnames) {

			// Print the names of files and directories
			//System.out.println(requestsname);
			File requestFile = new File(requestsDir, requestsname);
			
			String fileNameWithOutExt = FilenameUtils.removeExtension(requestsname);
			System.out.println(fileNameWithOutExt);
			
			String str = readLineByLine(requestFile.toString());
			
			
			
		}

		for (String wsdlName : wsdlNames) {

			// Print the names of files and directories
			System.out.println(wsdlName);
			File wsdlFile = new File(wsdlDir, wsdlName);

			List<Operation> operationList = getPortTypeOperations(wsdlFile.toURI().toString());

			ListIterator<Operation> listIterator = operationList.listIterator();

			while (listIterator.hasNext()) {
				System.out.println(listIterator.next().getName());
			}
		}
		
		
		

	}

	private static List<Operation> getPortTypeOperations(String wsdlUrl) {
		List<Operation> operationList = new ArrayList();

		try {
			WSDLFactory factory = WSDLFactory.newInstance();
			WSDLReader reader = factory.newWSDLReader();

			// pass the location/url to the reader for parsing and get list of operations
			Definition wsdlInstance = reader.readWSDL(wsdlUrl);

			Map<String, PortTypeImpl> defMap = wsdlInstance.getAllPortTypes();
			Collection<PortTypeImpl> collection = defMap.values();
			for (PortTypeImpl portType : collection) {
				operationList.addAll(portType.getOperations());
			}

		} catch (WSDLException e) {
			System.out.println("get wsdl operation fail.");
			e.printStackTrace();
		}
		return operationList;
	}

	private static String readLineByLine(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
}
