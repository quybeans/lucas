package com.lucas.scrapper.trangvang;

import com.lucas.db.DAOManager;
import com.lucas.db.TrangVangCategoryDAO;
import com.lucas.model.TrangVangCategory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryScrapper {
  public static void launch(DAOManager daoManager){
    List<TrangVangCategory> tvcs = new ArrayList<TrangVangCategory>();
    try
    {
      String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
      for (int i = 1 ; i <= 21; i++) {
        Document doc = Jsoup.connect("http://trangvangvietnam.com/findex.asp?page="+i).userAgent(userAgent).get();

        Elements newsHeadlines = doc.select("a[href~=http://trangvangvietnam.com/categories(.)+]");
        for (Element headline : newsHeadlines) {
          tvcs.add(new TrangVangCategory(0,headline.text(),"",headline.attr("href")));

        }
        System.out.println("http://trangvangvietnam.com/findex.asp?page="+i);
        Thread.sleep(3000);
      }
      writeToDb(daoManager,tvcs);
    } catch (IOException e)
    {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static void writeToDb(DAOManager daoManager, List<TrangVangCategory> tvcs) {
    try {
      TrangVangCategoryDAO tvcDao= daoManager.getTrangVangCategoryDAO(); // Open connection to db
      for (TrangVangCategory tvc : tvcs)
        daoManager.execute((DAOManager manager) -> tvcDao.create(tvc));

    } finally {
      daoManager.closeConnection();
    }
  }

}
