package ecplatform;

import java.io.File;

public class Test1 {

	public static void main(String[] args) {
		File file = new File("D:/dev/coding/ElectronicCommerce/webworkspace/easyui-demo/WebContent/demo");
		String[] list = file.list();
		File[] fileList = file.listFiles();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length; i++) {
			File f = fileList[i];
			if (!f.isDirectory()) {
				continue;
			}
			sb.append("<li>")
			  .append("<span>")
			  .append(list[i])
			  .append("</span>")
			  .append("<ul style=\"display:none;\">");
			String[] fC = f.list();
			for (int j = 0; j < fC.length; j++) {
				if (fC[j].indexOf(".html") == -1) {
					continue;
				}
				sb.append("<li>");
				sb.append(fC[j]);
				sb.append("</li>");
			}
			sb.append("</ul></li>");
		}
		System.out.println(sb.toString());
	}
}
