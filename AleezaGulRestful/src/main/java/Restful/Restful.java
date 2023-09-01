package Restful;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import FilmDAO.FilmDAO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import Film.Film;
import Film.Records;

@WebServlet("/Restful")

public class Restful extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filmsdata = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		String contenttype= request.getHeader("Content-type");
		
		String ct = request.getParameter("Content-type");
		System.out.println(filmsdata);
		
		
		if  (contenttype.equals("application/text") || ct.equals("application/text")){
			response.setContentType("application/text");
			String regex = "\\|";
			String[] s = filmsdata.split(regex);
			String title = s[0];
			String director = s[2];
			String review = s[4];
			String stars = s[3];
			int year = Integer.parseInt(s[1]);
			
			
			for(String x: s) {
				System.out.println(x);
			}

			Film films = new Film(07, title, year, director, stars, review);
			FilmDAO f = new FilmDAO();

			PrintWriter pw = response.getWriter(); // giving data back

			try {
				f.insertF(films);
				pw.write("Successfully inserted film");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.close();

		}
		
		if (ct.equals("application/xml")) {
			StringWriter sw = new StringWriter();
			try {
				JAXBContext jb = JAXBContext.newInstance(Film.class);
				Unmarshaller um = jb.createUnmarshaller();
				Film Unxml = (Film) um.unmarshal(new StringReader(filmsdata));

				FilmDAO film = new FilmDAO();
				PrintWriter out = response.getWriter();

				try {
					film.insertF(Unxml);
					out.write("Successfully inserted film");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.close();

			} catch (JAXBException error) {
				error.printStackTrace();
			}

		} else if (ct.equals("application/json")) {
			Gson unJson = new Gson();
			Film films = unJson.fromJson(filmsdata, Film.class);
			
			FilmDAO f = new FilmDAO();

			PrintWriter pw = response.getWriter(); // giving data back

			try {
				f.insertF(films);
				pw.write("Successfully inserted film");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.close();

		} 


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO Films = new FilmDAO(); // gets the filmDAo class for all the methods for the database
		ArrayList<Film> FilmsAll = Films.getAllFilms(); // gets all films method from filmDAO
		String datatype = request.getParameter("Accept");

		if (datatype.equals("application/xml")) {
			try {
				Records r = new Records(FilmsAll);
				StringWriter sw = new StringWriter();
				JAXBContext jb = JAXBContext.newInstance(Records.class);
				Marshaller m = jb.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.marshal(r, sw);
				System.out.print(sw.toString());
			} catch (JAXBException error) {
				error.printStackTrace();
			}
		} else if (datatype.equals("application/json")) {
			PrintWriter pw = response.getWriter();
			Gson js = new Gson();
			String convert = js.toJson(FilmsAll); // getting all films and converting to json
			pw.write(convert);
			pw.close();
		} else if (datatype.equals("application/text")) {
			PrintWriter pw = response.getWriter();
			String text= "";
			for(Film f:FilmsAll) {
				text+=f.getId()+"|"+f.getTitle()+"|"+f.getYear()+"|"+f.getStars()+"|"+f.getDirector()+"|"+f.getReview()+"\n";
				
			}
			pw.write(text);
			pw.close();

		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filmsdata = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		String ct = request.getParameter("Content-type");
		String contenttype= request.getHeader("Content-type");

		if  (contenttype.equals("application/text") || ct.equals("application/text")){
			response.setContentType("application/text");
			String regex = "\\|";
			String[] s = filmsdata.split(regex);
			int id=Integer.parseInt(s[0]);
			String title = s[1];
			String director = s[3];
			String review = s[5];
			String stars = s[4];
			int year = Integer.parseInt(s[2]);
			
			
			for(String x: s) {
				System.out.println(x);
			}

			Film films = new Film(id, title, year, director, stars, review);
			FilmDAO f = new FilmDAO();

			PrintWriter pw = response.getWriter(); // giving data back

			try {
				f.updateF(films);
				pw.write("Successfully updated film");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.close();

		}
		if (ct.equals("application/xml")) {
			StringWriter sw = new StringWriter();
			try {
				JAXBContext jb = JAXBContext.newInstance(Film.class);
				Unmarshaller um = jb.createUnmarshaller();
				Film Unxml = (Film) um.unmarshal(new StringReader(filmsdata));

				int id = Unxml.getId();
				String title = Unxml.getTitle();
				String director = Unxml.getDirector();
				String review = Unxml.getReview();
				String stars = Unxml.getStars();
				int year = Unxml.getYear();
				Film films = new Film(id, title, year, director, stars, review);
				FilmDAO f = new FilmDAO();
				PrintWriter out = response.getWriter();

				try {
					f.updateF(films);
					out.write("Successfully updated film");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.close();

			} catch (JAXBException error) {
				error.printStackTrace();
			}

		} else if (ct.equals("application/json")) {
			Gson unJson = new Gson();

			Film unJsonfilms = unJson.fromJson(filmsdata, Film.class);
			int id = unJsonfilms.getId();
			String title = unJsonfilms.getTitle();
			String director = unJsonfilms.getDirector();
			String review = unJsonfilms.getReview();
			String stars = unJsonfilms.getStars();
			int year = unJsonfilms.getYear();
			Film films = new Film(id, title, year, director, stars, review);
			FilmDAO film = new FilmDAO();

			PrintWriter pw = response.getWriter(); // giving data back

			try {
				film.updateF(films);
				pw.write("Successfully inserted film");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.close();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filmsdata = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		String ct = request.getParameter("Content-type");
		String contenttype= request.getHeader("Content-type");
		
		if  (contenttype.equals("application/text") || ct.equals("application/text")){
			response.setContentType("application/text");
			String regex = "\\|";
			String[] s = filmsdata.split(regex);
			int id=Integer.parseInt(s[0]);
			
			
			
			for(String x: s) {
				System.out.println(x);
			}
			Film films=new Film();
			films.setId(id);
			FilmDAO f = new FilmDAO();
			

			PrintWriter pw = response.getWriter(); // giving data back

			try {
				f.deleteF(films);
				pw.write("Successfully deleted film");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.close();

		}

		if (ct.equals("application/xml")) {
			StringWriter sw = new StringWriter();
			try {
				JAXBContext jb = JAXBContext.newInstance(Film.class);
				Unmarshaller um = jb.createUnmarshaller();
				Film Unxml = (Film) um.unmarshal(new StringReader(filmsdata));

				int id = Unxml.getId();
				Film films = new Film();
				films.setId(id);
				FilmDAO f = new FilmDAO();
				PrintWriter out = response.getWriter();

				try {
					f.deleteF(films);
					out.write("Successfully deleted film");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.close();

			} catch (JAXBException error) {
				error.printStackTrace();
			}

		} else if (ct.equals("application/json")) {
			Gson unJson = new Gson();

			Film unJsonfilms = unJson.fromJson(filmsdata, Film.class);
			int id = unJsonfilms.getId();
			Film films = new Film();
			films.setId(id);
			FilmDAO film = new FilmDAO();

			PrintWriter pw = response.getWriter(); // giving data back

			try {
				film.deleteF(films);
				pw.write("Successfully deleted film");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.close();
		}
	

}}