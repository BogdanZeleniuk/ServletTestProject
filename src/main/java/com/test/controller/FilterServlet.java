package com.test.controller;

import com.test.model.FileModel;
import com.test.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FilterServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(FilterServlet.class);
    private FileModel model = new FileModel();
    private ParamUtil util = new ParamUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String text = util.getTextParam(req.getParameter("q"));
        int limit = util.getLimitParam(req.getParameter("limit"));
        int length = util.getLengthParam(req.getParameter("length"));
        boolean includeMetaData = util.includeMetaData(req.getParameter("includeMetaData"));

        LOG.info("Request`s parameters are:limit-"+limit+", text-"+text+", length"+length+", includeMetaData-"+includeMetaData);

        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();

        printWriter.println("Response content type is " + resp.getContentType());
        printWriter.println();
        printWriter.println();
        printWriter.println(model.getFilteredObject(limit, text, length, includeMetaData).toString());
        printWriter.close();

    }
}
