package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DataFormController;


public class DataFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);	
		}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
	}
}
	
	
	
	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try{
			String username = req.getSession().getAttribute("username").toString();
			if(username != null){
				resp.sendRedirect(req.getContextPath() + "/user");
			}
		}
		catch (NullPointerException e){
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String gardenName = null;
		String date = null;
		String startTime = null;
		String endTime = null;
		String temperature = null;
		String wind = null;
		String cloudCover  = null;
		String plantType = null;
		String pollinatorType = null;
		String pollinatorCount  = null;
		String avgHeightofPlot = null;
		String sizePlot = null;
		String bloomsOpen = null;
		String flowerCoverage = null;
		String plantVigor  = null;
		
		String buttonPress = null;
		boolean submitted = false;
		String errorMessage = null;
		
		DataFormController data = new DataFormController();
		

		buttonPress = req.getParameter("dataSubmit");
		

		if(buttonPress != null){
			if(buttonPress.toLowerCase().equals("logout")){
				req.getSession().setAttribute("loggedin",false);
				req.getSession().setAttribute("username", null);
				req.getSession().setAttribute("login_id", -1);
				req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
			}
			else if(buttonPress.toLowerCase().equals("submit")){
				
				gardenName = req.getParameter("gardenName");
				date = req.getParameter("date");
				startTime = req.getParameter("startTime");
				endTime = req.getParameter("endTime");
				temperature = req.getParameter("temperature");
				wind = req.getParameter("wind");
				cloudCover  = req.getParameter("cloudCover");
				plantType = req.getParameter("plantType");
				pollinatorType = req.getParameter("pollinatorType");
				pollinatorCount  = req.getParameter("pollinatorCount");
				avgHeightofPlot = req.getParameter("avgHeightofPlot");
				sizePlot = req.getParameter("sizePlot");
				bloomsOpen = req.getParameter("bloomsOpen");
				flowerCoverage = req.getParameter("flowerCoverage");
				plantVigor  = req.getParameter("plantVigor");


				if("".equals(gardenName) || gardenName == null ||
						"".equals(date) || date == null ||
						"".equals(startTime) || startTime == null ||
						"".equals(endTime) || endTime == null ||
						"".equals(temperature) || temperature == null ||
						"".equals(wind) || wind == null ||
						"".equals(cloudCover) || cloudCover == null ||
						"".equals(plantType) || plantType == null ||
						"".equals(pollinatorType) || pollinatorType == null ||
						"".equals(pollinatorCount) || pollinatorCount == null ||
						"".equals(avgHeightofPlot) || avgHeightofPlot == null ||
						"".equals(sizePlot) || sizePlot == null ||
						"".equals(bloomsOpen) || bloomsOpen == null ||
						"".equals(flowerCoverage) || flowerCoverage == null ||
						"".equals(plantVigor) || plantVigor == null) {
					errorMessage = "Invaild Data Entry";
				}
			}
		}
	}
}

*/
