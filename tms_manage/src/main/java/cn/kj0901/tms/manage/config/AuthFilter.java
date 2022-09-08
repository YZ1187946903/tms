package cn.kj0901.tms.manage.config;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.util.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * KJ0901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/18 14:36
 */
@Slf4j
@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
@Component
public class AuthFilter implements Filter {

	private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Autowired
	private TokenUtil tokenUtil;

	private static final String excludedPaths = "/admin/*";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("TokenFilter初始化完成");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse rep = (HttpServletResponse) servletResponse;
		boolean isFilter = false;
		ResultJson result = null;

		//获取header-token信息及相关信息
		String token = req.getHeader("token");

		String servletPath = req.getServletPath();

		//跳过不需要过滤的路径
		if (excludedPaths != null && excludedPaths.trim().length() > 0) {
			for (String excludedPath : excludedPaths.split(",")) {
				String uriPattern = excludedPath.trim();

				if (antPathMatcher.match(uriPattern, servletPath)) {
					filterChain.doFilter(servletRequest, servletResponse);
					return;
				}

			}
		}

		//验证token
		if (token == null || "".equals(token) || !tokenUtil.checkToken(token)) {
			result = ResultJson.err(301, "未登录或登录状态异常");
			rep.setCharacterEncoding("UTF-8");
			PrintWriter writer = rep.getWriter();
			String jsonStr = JSONObject.toJSONString(result);
			log.info(jsonStr);
			writer.print(jsonStr);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}

	}

	@Override
	public void destroy() {

	}
}
