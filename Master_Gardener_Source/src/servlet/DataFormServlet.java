package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import controller.DataFormController;



public class DataFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String gardenName = null;
        String date = null;
        int startTime = 0;
        int endTime = 0;
        int temperature = 0;
        String wind = null;
        String cloudCover = null;
        String plantType = null;
        String pollinatorType = null;
        int pollinatorCount = 0;
        int avgHeightofPlot = 0;
        int sizePlot = 0;
        String bloomsOpen = null;
        int flowerCoverage = 0;
        String plantVigor = null;
        String comments = null;
        String errorMessage = null;

        gardenName = req.getParameter("gardenname");
        date = req.getParameter("date");
        startTime = getIntFromParameter(req.getParameter("starttime"));
        endTime = getIntFromParameter(req.getParameter("endtime"));
        temperature = getIntFromParameter(req.getParameter("temperature"));
        wind = req.getParameter("wind");
        cloudCover = req.getParameter("cloudcover");
        plantType = req.getParameter("planttype");
        pollinatorType = req.getParameter("pollinatortype");
        pollinatorCount = getIntFromParameter(req.getParameter("pollinatorcount"));
        avgHeightofPlot = getIntFromParameter(req.getParameter("avgheightofplot"));
        sizePlot = getIntFromParameter(req.getParameter("sizeplot"));
        bloomsOpen = req.getParameter("bloomsopen");
        flowerCoverage = getIntFromParameter(req.getParameter("flowercoverage"));
        plantVigor = req.getParameter("plantvigor");
        comments = req.getParameter("comments");

        if ("".equals(gardenName) || gardenName == null) {
            errorMessage = "Enter the name of the garden";
            System.out.printf("%s", errorMessage);
            gardenName = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(date) || date == null) {
            errorMessage = "Enter the date";
            System.out.printf("%s", errorMessage);
            date = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(startTime) || startTime == 0) {
            errorMessage = "Enter a start time";
            System.out.printf("%s", errorMessage);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(endTime) || endTime == 0) {
            errorMessage = "Enter a end time";
            System.out.printf("%s", errorMessage);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(temperature) || temperature == 0) {
            errorMessage = "Enter a temperature";
            System.out.printf("%s", errorMessage);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(wind) || wind == null) {
            errorMessage = "Enter a wind condition";
            System.out.printf("%s", errorMessage);
            wind = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(cloudCover) || cloudCover == null) {
            errorMessage = "Enter a cloud cover condition";
            System.out.printf("%s", errorMessage);
            cloudCover = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(plantType) || plantType == null) {
            errorMessage = "Enter the plant type";
            System.out.printf("%s", errorMessage);
            plantType = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(pollinatorType) || pollinatorType == null) {
            errorMessage = "Enter the pollinator type";
            System.out.printf("%s", errorMessage);
            pollinatorType = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(pollinatorCount) || pollinatorCount == 0) {
            errorMessage = "Enter the count of pollinators";
            System.out.printf("%s", errorMessage);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(avgHeightofPlot) || avgHeightofPlot == 0) {
            errorMessage = "Enter the average height of the plot";
            System.out.printf("%s", errorMessage);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(sizePlot) || sizePlot == 0) {
            errorMessage = "Enter the size of the plot";
            System.out.printf("%s", errorMessage);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(bloomsOpen) || bloomsOpen == null) {
            errorMessage = "Enter blooms open";
            System.out.printf("%s", errorMessage);
            bloomsOpen = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(flowerCoverage) || flowerCoverage == 0) {
            errorMessage = "Enter the coverage of flowers";
            System.out.printf("%s", errorMessage);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(plantVigor) || plantVigor == null) {
            errorMessage = "Enter the plant vigor";
            System.out.printf("%s", errorMessage);
            plantVigor = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else if ("".equals(comments) || comments == null) {
            errorMessage = "Enter what comments you may have";
            System.out.printf("%s", errorMessage);
            comments = null;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/_view/dataInput.jsp").forward(req, resp);
        } else {
            //Not sure how to send to table since it's multiple tables


        }
        req.setAttribute("gardenname", gardenName);
        req.setAttribute("date", date);
        req.setAttribute("starttime", startTime);
        req.setAttribute("endtime", endTime);
        req.setAttribute("temperature", temperature);
        req.setAttribute("wind", wind);
        req.setAttribute("cloudcover", cloudCover);
        req.setAttribute("planttype", plantType);
        req.setAttribute("pollinatortype", pollinatorType);
        req.setAttribute("pollinatorcount", pollinatorCount);
        req.setAttribute("avgheightofplot", avgHeightofPlot);
        req.setAttribute("sizeplot", sizePlot);
        req.setAttribute("bloomsopen", bloomsOpen);
        req.setAttribute("flowercoverage", flowerCoverage);
        req.setAttribute("plantvigor", plantVigor);
        req.setAttribute("comments", comments);
    }
    private int getIntFromParameter(String s) {
        if (s == null || s.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }
}