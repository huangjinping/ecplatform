package cn.cooperlink.ecplatform.index.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cooperlink.ecplatform.security.AuthHelper;
import cn.cooperlink.ecplatform.security.Permission;
import cn.cooperlink.ecplatform.system.FuncTreeUtil;
import cn.cooperlink.ecplatform.system.service.FunctionService;

@Controller
@RequestMapping(value="/", method={RequestMethod.GET, RequestMethod.POST})
public class IndexController {
    
	public static final String INDEX_PAGE = "index";

    /** functionService */
    @Resource
    private FunctionService functionService;

	@RequestMapping
	public String index(ModelMap model) {
		buildMenu(model);
		return INDEX_PAGE;
	}
	
	/**
	 * @Title: 构建左侧菜单
	 * @Description:
	 * @param model
	 */
	private void buildMenu(ModelMap model) {		
    	List<Permission> list = FuncTreeUtil.buildFuncTreeList(
    			AuthHelper.getAuthInfo().getPermissionList(), 
    			functionService.findAllCatalog());
		try {
	    	// TODO 菜单加载暂时如此实现，后期熟悉了easyui后再考虑改进
	    	Map<Long, List<Permission>> menuMap = functionService.separateMenu(list);
	    	List<Permission> barMenuList = menuMap.get(new Long(0));
	    	StringBuilder html = new StringBuilder();
	    	List<Permission> treeRoot = null;
	    	Map<Long, String> treeMenuMap = new HashMap<Long, String>();
	    	for (Permission perm : barMenuList) {
	    		treeRoot = menuMap.get(perm.getId());
	    		if (treeRoot != null) {
	    			html.append("<ul  id=\"menu-bar-")
		    			.append(perm.getId())
		    			.append("\" class=\"easyui-tree\">");
	    			buildMenuTree(html, treeRoot, menuMap);
	    			html.append("</ul>");
	    			treeMenuMap.put(perm.getId(), html.toString());
	    			html.setLength(0);
	    		}
	    	}
	    	model.put("barMenuList", barMenuList);
	    	model.put("treeMenuMap", treeMenuMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 菜单的树形部分
	 *
	 * @param html
	 * @param childreen
	 * @param menuMap
	 */
	private void buildMenuTree(StringBuilder html, 
			List<Permission> childreen,
			Map<Long, List<Permission>> menuMap) {
		List<Permission> cdrs;
		for (Permission perm : childreen) {
			html.append("<li>");
			cdrs = menuMap.get(perm.getId());
			if (cdrs != null) {
				html.append("<span>")
					.append(perm.getName())
					.append("</span>")
					.append("<ul>");
				buildMenuTree(html, cdrs, menuMap);
				html.append("</ul></li>");
			} else {
				html.append("<a href=\"#\" onclick=\"show('")
					.append(perm.getUrl())
					.append("','")
					.append(perm.getName())
					.append("')\">")
					.append(perm.getName())
					.append("</a></li>");
			}
		}
		
	}
}
