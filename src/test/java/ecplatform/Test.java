package ecplatform;

import java.io.File;

public class Test {

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
			sb.append("<li>\r\n")
			  .append("\t<span>")
			  .append(list[i])
			  .append("</span>\r\n")
			  .append("\t<ul sytle=\"display:none;\">\r\n");
			String[] fC = f.list();
			for (int j = 0; j < fC.length; j++) {
				if (fC[j].indexOf(".html") == -1) {
					continue;
				}
				sb.append("\t\t<li>");
				sb.append(fC[j]);
				sb.append("</li>\r\n");
			}
			sb.append("\t</ul>\r\n</li>\r\n");
		}
		System.out.println(sb.toString());
	}
}
