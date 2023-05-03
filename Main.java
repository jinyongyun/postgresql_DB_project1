import javax.xml.transform.Result;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        /// if you have a error in this part, check jdbc driver(.jar file)

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres", "cse3207");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        /// if you have a error in this part, check DB information (db_name, user name, password)

        if (connection != null) {
            System.out.println(connection);
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }


        /////////////////////////////////////////////////////
        ////////// write your code on this ////////////
        Statement stmt = connection.createStatement();
        ResultSet resultSet = null;


        // 1.Create the tables
        //stmt.executeUpdate("DROP TABLE movieGenre,movieObtain,actorObtain,casting,make,customerRate,directorObtain");
        //stmt.executeUpdate("DROP TABLE director,actor,movie,award,genre,customer");
        stmt.executeUpdate("create table movie(movieID serial primary key, movieName varchar(30), releaseYear integer, releaseMonth integer, releaseDate integer, publisherName varchar(30), avgRate double precision) ");
        stmt.executeUpdate("create table director(directorID serial primary key, directorName varchar(30), dateOfBirth date, dateOfDeath date)");
        stmt.executeUpdate("create table actor(actorID serial primary key, actorName varchar(30), dateOfBirth date, dateOfDeath date, gender varchar(10))");
        stmt.executeUpdate("create table customer(customerID serial primary key, customerName varchar(30), dateOfBirth date, Gender varchar(10))");
        stmt.executeUpdate("create table award(awardID serial primary key, awardName varchar(60))");
        stmt.executeUpdate("create table genre(genreName varchar(20) primary key)");
        stmt.executeUpdate("create table movieGenre(movieID integer, genreName varchar(20), foreign key (movieID) references movie on delete cascade, foreign key (genreName) references genre on delete cascade)");
        stmt.executeUpdate("create table movieObtain(movieID integer, awardID integer, year integer, foreign key (movieID) references movie on delete cascade on update cascade, foreign key (awardID) references award on delete cascade on update cascade)");
        stmt.executeUpdate("create table actorObtain(actorID integer, awardID integer, year integer, foreign key (actorID) references actor on delete cascade on update cascade, foreign key (awardID) references award on delete cascade on update cascade)");
        stmt.executeUpdate("create table directorObtain(directorID integer, awardID integer, year integer, foreign key (directorID) references director on delete cascade on update cascade, foreign key (awardID) references award on delete cascade on update cascade)");
        stmt.executeUpdate("create table casting(movieID integer, actorID integer, role varchar(20), foreign key (movieID) references movie on delete cascade on update cascade, foreign key (actorID) references actor on delete cascade on update cascade)");
        stmt.executeUpdate("create table make(movieID integer, directorID integer, foreign key (movieID) references movie on delete cascade on update cascade, foreign key (directorID) references director on delete cascade on update cascade)");
        stmt.executeUpdate("create table customerRate(customerID integer,movieID integer, rate integer, foreign key (customerID) references customer on delete cascade on update cascade, foreign key (movieID) references movie on delete cascade on update cascade)");
        System.out.println("Table created!");
        //1.5 insert the proper data based on the provided data
        stmt.executeUpdate("insert into Director(directorName,dateOfBirth,dateOfDeath) values ('Francis Ford Coppola','1939-04-07',null)");
        stmt.executeUpdate("insert into Director(directorName,dateofBirth,dateOfDeath) values ('James Cameron','1954-8-16',null)");
        stmt.executeUpdate("insert into Director(directorName,dateofBirth,dateOfDeath) values ('Christopher Nolan','1970-7-30',null)");

        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values ('Marlon Brando','1924-4-3','2004-7-1','Male')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values('Robert Duvall','1931-1-5',null,'Male')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values('Al Pacino','1940-4-25',null,'Male')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values('Sam Worthington','1976-8-2',null,'Male')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values('Zoe Saldana','1978-6-19',null,'Female')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values ('Leonardo DiCaprio','1974-11-11',null,'Male')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values('Kate Winslet','1975-10-5',null,'Female')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values('Fionn Whitehead','1997-07-18',null,'Male')");
        stmt.executeUpdate("insert into Actor(actorName,dateOfBirth,dateOfDeath,gender) values ('Tom Hardy','1997-09-15',null,'Male')");

        stmt.executeUpdate("insert into customer(customerName,dateOfBirth,gender) values('Bob','1996-08-21','Male')");
        stmt.executeUpdate("insert into customer(customerName,dateOfBirth,gender) values('John','1989-06-23','Male')");
        stmt.executeUpdate("insert into customer(customerName,dateOfBirth,gender) values('Jack','1970-05-04','Male')");
        stmt.executeUpdate("insert into customer(customerName,dateOfBirth,gender) values('Jil','1987-04-27','Female')");
        stmt.executeUpdate("insert into customer(customerName,dateOfBirth,gender) values('Bell','2001-12-14','Female')");

        stmt.executeUpdate("insert into movie(movieName,releaseYear,releaseMonth,releaseDate,publisherName,avgRate) values('Apocalypse Now',1979,5,19,'Omni Zoetrope',0)");
        stmt.executeUpdate("insert into movie(movieName,releaseYear,releaseMonth,releaseDate,publisherName,avgRate) values ('The Godfather',1972,3,14,'Paramount Pictures',0)");
        stmt.executeUpdate("insert into movie(movieName,releaseYear,releaseMonth,releaseDate,publisherName,avgRate) values ('Avatar',2009,12,10,'20th Century Fox',0)");
        stmt.executeUpdate("insert into movie(movieName,releaseYear,releaseMonth,releaseDate,publisherName,avgRate) values ('Titanic',1997,11,01,'Paramount Pictures',0)");
        stmt.executeUpdate("insert into movie(movieName,releaseYear,releaseMonth,releaseDate,publisherName,avgRate) values ('Dunkirk',2017,7,13,'Warner Bros. Pictures',0)");
        stmt.executeUpdate("insert into movie(movieName,releaseYear,releaseMonth,releaseDate,publisherName,avgRate) values ('Inception',2010,7,8,'Warner Bros. Pictures',0)");

        stmt.executeUpdate("insert into genre values('Fantasy')");
        stmt.executeUpdate("insert into genre values('Romance')");
        stmt.executeUpdate("insert into genre values ('Adventure')");
        stmt.executeUpdate("insert into genre values ('Drama')");
        stmt.executeUpdate("insert into genre values ('Action')");
        stmt.executeUpdate("insert into genre values ('Mystery')");
        stmt.executeUpdate("insert into genre values ('Thriller')");
        stmt.executeUpdate("insert into genre values ('War')");
        stmt.executeUpdate("insert into genre values ('Crime')");
        stmt.executeUpdate("insert into genre values ('Sci-Fi')");


        stmt.executeUpdate("insert into movieGenre values (1,'Drama')");
        stmt.executeUpdate("insert into movieGenre values (1, 'Mystery')");
        stmt.executeUpdate("insert into movieGenre values (1, 'War')");
        stmt.executeUpdate("insert into movieGenre values (2, 'Crime')");
        stmt.executeUpdate("insert into movieGenre values (2, 'Drama')");
        stmt.executeUpdate("insert into movieGenre values (3, 'Action')");
        stmt.executeUpdate("insert into movieGenre values (3, 'Adventure')");
        stmt.executeUpdate("insert into movieGenre values (3, 'Fantasy')");
        stmt.executeUpdate("insert into movieGenre values (4, 'Drama')");
        stmt.executeUpdate("insert into movieGenre values (4, 'Romance')");
        stmt.executeUpdate("insert into movieGenre values (5, 'Action')");
        stmt.executeUpdate("insert into movieGenre values (5, 'Drama')");
        stmt.executeUpdate("insert into movieGenre values (5, 'War')");
        stmt.executeUpdate("insert into movieGenre values (5, 'Thriller')");
        stmt.executeUpdate("insert into movieGenre values (6, 'Action')");
        stmt.executeUpdate("insert into movieGenre values (6, 'Adventure')");
        stmt.executeUpdate("insert into movieGenre values (6, 'Sci-Fi')");

        stmt.executeUpdate("insert into casting values(1,1,'Main actor')");
        stmt.executeUpdate("insert into casting values(1,2,'Supporting actor')");
        stmt.executeUpdate("insert into casting values(2,1,'Main actor')");
        stmt.executeUpdate("insert into casting values(2,3,'Main actor')");
        stmt.executeUpdate("insert into casting values(3,4,'Main actor')");
        stmt.executeUpdate("insert into casting values(3,5,'Main actor')");
        stmt.executeUpdate("insert into casting values(4,6,'Main actor')");
        stmt.executeUpdate("insert into casting values(4,7,'Main actor')");
        stmt.executeUpdate("insert into casting values(5,8,'Main actor')");
        stmt.executeUpdate("insert into casting values(5,9,'Main actor')");
        stmt.executeUpdate("insert into casting values(6,6,'Main actor')");

        stmt.executeUpdate("insert into make values (1,1)");
        stmt.executeUpdate("insert into make values (2,1)");
        stmt.executeUpdate("insert into make values (3,2)");
        stmt.executeUpdate("insert into make values (4,2)");
        stmt.executeUpdate("insert into make values (5,3)");
        stmt.executeUpdate("insert into make values (6,3)");

        System.out.println("Initial data inserted!");

        ///////////////////////////////////////////////////
        //trigger made
        stmt.executeUpdate("CREATE OR REPLACE FUNCTION update_avgRate()\n" +
                "         RETURNS TRIGGER AS $$\n" +
                " BEGIN\n" +
                " UPDATE movie\n" +
                "SET avgRate = (\n" +
                "    SELECT AVG(rate)\n" +
                "FROM customerRate\n" +
                "WHERE movieID = NEW.movieID\n" +
                ")\n" +
                "WHERE movieID = NEW.movieID;\n" +
                "RETURN NEW;\n" +
                "END;\n" +
                "$$ LANGUAGE plpgsql;");


        stmt.executeUpdate("CREATE TRIGGER update_avgRate AFTER INSERT ON customerRate\n" +
                "FOR EACH ROW\n" +
                 "EXECUTE FUNCTION update_avgRate();");


                ////////////////////////////////////////////////////
        System.out.println("\n\n\n\n\n\n");
      System.out.println("2.insert the proper data from the following statements");
        System.out.println("problem:" +"2.1");
        System.out.println("Statement: Marlon Brando won the \"Best Actor in a Leading Role\" award in 1973");
        System.out.println("Translated SQL: insert into award(awardName) values ('Best Actor in a Leading Role')");
        System.out.println("Translated SQL: select awardID from award where awardName = 'Best Actor in a Leading Role')");
        System.out.println("Translated SQL: select actorID from actor where actorName = 'Marlon Brando'");
        System.out.println("Translated SQL: insert into actorObtain values (\" + marlonid + \",\" + balrawardid + \",1973)");
        stmt.executeUpdate("insert into award(awardName) values ('Best Actor in a Leading Role')");
        ResultSet result0 = stmt.executeQuery("select awardID from award where awardName = 'Best Actor in a Leading Role'");
        result0.next();
        int balrawardid = result0.getInt("awardID");
        ResultSet result1 = stmt.executeQuery("select actorID from actor where actorName = 'Marlon Brando'");
        result1.next();
        int marlonid = result1.getInt("actorID");
        stmt.executeUpdate("insert into actorObtain values (" + marlonid + "," + balrawardid + ",1973)");
        System.out.println("Updated tables: award, actorObtain");
        ResultSet result2 = stmt.executeQuery("select awardID,awardName from award where awardID = 1");
        while(result2.next()){
            System.out.println("----------------< award >----------------");
            System.out.println("awardID                 awardName");
            System.out.println(result2.getInt("awardID") + "              " + result2.getString("awardName"));
        }
        ResultSet result3 = stmt.executeQuery("select actorID,awardID,year from actorObtain where actorID = 1");
        while(result3.next()){
            System.out.println("----------------< actorObtain >----------------");
            System.out.println("actorID             awardID             year");
            System.out.println(result3.getInt("actorID")+"                  "+result3.getInt("awardID")+"                  "+result3.getInt("year"));
        }


        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +2.2);
        System.out.println("Statement:Robert Duvall won the \"Best Supporting Actor\" award in 1980");
        System.out.println("Translated SQL: insert into award values (2, 'Best Supporting Actor')");
        System.out.println("Translated SQL: insert into actorObtain values (2,2,1980)");
        stmt.executeUpdate("insert into award values (2, 'Best Supporting Actor')");
        stmt.executeUpdate("insert into actorObtain values (2,2,1980)");
        System.out.println("Updated tables: award, actorObtain");
        ResultSet result4 = stmt.executeQuery("select awardID,awardName from award where awardID = 2");
        while(result4.next()) {
            System.out.println("----------------< award >----------------");
            System.out.println("awardID                 awardName");
            System.out.println(result4.getInt("awardID") + "                    " + result4.getString("awardName"));
        }

        ResultSet result5 = stmt.executeQuery("select actorID,awardID,year from actorObtain where actorID = 2");
        while(result5.next()){
            System.out.println("----------------< actorObtain >----------------");
            System.out.println("actorID             awardID             year");
            System.out.println(result5.getInt("actorID")+"                    "+result5.getInt("awardID")+"                    "+result5.getInt("year"));

        }
        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +2.3);
        System.out.println("Statement:The movie Francis Ford Coppola won the \"Best Direction\" award in 1980");
        System.out.println("Translated SQL: insert into award values (3, 'Best Direction')");
        System.out.println("insert into directorObtain values (1,3,1980)");

        stmt.executeUpdate("insert into award values (3, 'Best Direction')");
        stmt.executeUpdate("insert into directorObtain values (1,3,1980)");
        System.out.println("Updated tables: award, directorObtain");

        ResultSet result6 = stmt.executeQuery("select awardID,awardName from award where awardID = 3");
        while(result6.next()){
            System.out.println("----------------< award >----------------");
            System.out.println("awardID                 awardName");
            System.out.println(result6.getInt("awardID") + "                    " + result6.getString("awardName"));
        }
        ResultSet result7 = stmt.executeQuery("select directorID,awardID,year from directorObtain where directorID = 1 and awardID = 3");
        while(result7.next()){
            System.out.println("----------------< directorObtain >----------------");
            System.out.println("directorID             awardID             year");
            System.out.println(result7.getInt("directorID")+"                   "+result7.getInt("awardID")+"                   "+result7.getInt("year"));

        }

        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +2.4);
        System.out.println("Statement:James Cameron won the \"Best Director\" award in 1998 and 2010, \"Best Writing\" award in 2010");
        System.out.println("Translated SQL: insert into award values (4, 'Best Director') ");
        System.out.println("Translated SQL: insert into award values (5, 'Best Writing') ");
        System.out.println("Translated SQL: insert into directorObtain values (2,4,1998) ");
        System.out.println("Translated SQL: insert into directorObtain values (2,4,2010) ");
        System.out.println("Translated SQL: insert into directorObtain values (2,5,2010) ");
        stmt.executeUpdate("insert into award values (4, 'Best Director')");
        stmt.executeUpdate("insert into award values (5, 'Best Writing')");
        stmt.executeUpdate("insert into directorObtain values (2,4,1998)");
        stmt.executeUpdate("insert into directorObtain values (2,4,2010)");
        stmt.executeUpdate("insert into directorObtain values (2,5,2010)");
        System.out.println("Updated tables: award, directorObtain");
        ResultSet result8 = stmt.executeQuery("select awardID,awardName from award where awardID = 4 and awardID = 5");
        while(result8.next()){
            System.out.println("----------------< award >----------------");
            System.out.println("awardID                 awardName");
            System.out.println(result8.getInt("awardID") + "                        " + result8.getString("awardName"));
        }
        ResultSet result9 = stmt.executeQuery("select directorID,awardID,year from directorObtain where directorID = 2");
        System.out.println("----------------< directorObtain >----------------");
        while(result9.next()){

            System.out.println("directorID             awardID             year");
            System.out.println(result9.getInt("directorID")+"                    "+result9.getInt("awardID")+"                     "+result9.getInt("year"));

        }


        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +2.5);
        System.out.println("The movie Titanic won the \"Best Picture\" award in 1998");
        System.out.println("Translated SQL:insert into award values (6,'Best Picture')");
        System.out.println("insert into movieObtain values (4,6,1998)");
        stmt.executeUpdate("insert into award values (6,'Best Picture')");
        stmt.executeUpdate("insert into movieObtain values (4,6,1998)");

        System.out.println("Updated tables: award, movieObtain");
        ResultSet result10 = stmt.executeQuery("select awardID,awardName from award where awardID = 6");
        while(result10.next()){
            System.out.println("----------------< award >----------------");
            System.out.println("awardID                 awardName");
            System.out.println(result10.getInt("awardID") + "                        " + result10.getString("awardName"));
        }
        ResultSet result11 = stmt.executeQuery("select movieID,awardID,year from movieObtain where awardID = 6 and movieID = 4");
        System.out.println("----------------< movieObtain >----------------");
        while(result11.next()){

            System.out.println("movieID             awardID             year");
            System.out.println(result11.getInt("movieID")+"                    "+result11.getInt("awardID")+"                     "+result11.getInt("year"));

        }


        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +2.6);
        System.out.println("Statement:The movie Dunkirk won the \"Best Sound\" award in 2018");
        System.out.println("Translated SQL: insert into award values (7,'Best Sound')");
        System.out.println("Translated SQL: insert into movieObtain values (5,7,2018)");
        stmt.executeUpdate("insert into award values (7,'Best Sound')");
        stmt.executeUpdate("insert into movieObtain values (5,7,2018)");
        System.out.println("Updated tables: actor, award, actorObtain");
        ResultSet result12 = stmt.executeQuery("select awardID,awardName from award where awardID = 7");
        while(result12.next()){
            System.out.println("----------------< award >----------------");
            System.out.println("awardID                 awardName");
            System.out.println(result12.getInt("awardID") + "                        " + result12.getString("awardName"));
        }
        ResultSet result13 = stmt.executeQuery("select movieID,awardID,year from movieObtain where awardID = 7 and movieID = 5");
        System.out.println("----------------< movieObtain >----------------");
        while(result13.next()){

            System.out.println("movieID             awardID             year");
            System.out.println(result13.getInt("movieID")+"                    "+result13.getInt("awardID")+"                     "+result13.getInt("year"));

        }
        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +2.7);
        System.out.println("Statement:Leonardo DiCaprio won the \"Best Performance by an Actor in a Leading Role\" award in 2016");
        System.out.println("Translated SQL: insert into award values (8, 'Best Performance by an Actor in a Leading Role')");
        System.out.println("Translated SQL: insert into actorObtain values (6,8,2016)')");
        stmt.executeUpdate("insert into award values (8, 'Best Performance by an Actor in a Leading Role')");
        stmt.executeUpdate("insert into actorObtain values (6,8,2016)");
        System.out.println("Updated tables: award, actorObtain");
        ResultSet result14 = stmt.executeQuery("select awardID,awardName from award where awardID =8");
        while(result14.next()){
            System.out.println("----------------< award >----------------");
            System.out.println("awardID                 awardName");
            System.out.println(result14.getInt("awardID") + "                        " + result14.getString("awardName"));
        }
        ResultSet result15 = stmt.executeQuery("select actorID,awardID,year from actorObtain where awardID = 8 and actorID = 6");
        System.out.println("----------------< actorObtain >----------------");
        while(result15.next()){

            System.out.println("actorID             awardID             year");
            System.out.println(result15.getInt("actorID")+"                    "+result15.getInt("awardID")+"                     "+result15.getInt("year"));

        }



        System.out.println("\n\n\n\n\n\n");
        System.out.println("3. insert date to the proper tables based on the following statements and update avgRate if necessary");

        System.out.println("problem:" +3.1);
        System.out.println("Statement:Jill rates 5 to \"Apocalypse Now\"");
        System.out.println("Translated SQL: insert into customerRate values (4,1,5)");
        stmt.executeUpdate("insert into customerRate values (4,1,5)");
        System.out.println("Updated tables: customerRate, movie(Trigger used)");
        ResultSet result16 = stmt.executeQuery("select customerID,movieID,rate from customerRate where customerID = 4 and rate = 5");
        while(result16.next()){
            System.out.println("----------------< customerRate >----------------");
            System.out.println("customerID             movieID             rate");
            System.out.println(result16.getInt("customerID")+"                       "+result16.getInt("movieID")+"                     "+result16.getInt("rate"));

        }
        ResultSet result17 = stmt.executeQuery("select movieID,movieName,avgRate from movie where movieName = 'Apocalypse Now'");
        System.out.println("----------------< movie >----------------");
        while(result17.next()){

            System.out.println("movieID             movieName              avgRate");
            System.out.println(result17.getInt("movieID")+"                    "+result17.getString("movieName")+"               "+result17.getInt("avgRate"));

        }
        System.out.println("\n\n\n\n\n\n");
         System.out.println("problem:" +3.2);
        System.out.println("Statement:Bill rates 5 to the movies whose director is \"James Cameron\"");
        System.out.println("Translated SQL: insert into customerRate values (5,3,5)");
        System.out.println("Translated SQL: insert into customerRate values (5,4,5)");
        stmt.executeUpdate("insert into customerRate values (5,3,5)");
        stmt.executeUpdate("insert into customerRate values (5,4,5)");
        System.out.println("Updated tables: customerRate, movie(Trigger used)");
        ResultSet result18 = stmt.executeQuery("select customerID,movieID,rate from customerRate where customerID = 5");
        System.out.println("----------------< customerRate >----------------");
        while(result18.next()){

            System.out.println("customerID             movieID             rate");
            System.out.println(result18.getInt("customerID")+"                       "+result18.getInt("movieID")+"                     "+result18.getInt("rate"));

        }
        ResultSet result19 = stmt.executeQuery("select movieID,movieName,avgRate from movie where movieID = 3 or movieID = 4");
        System.out.println("----------------< movie >----------------");
        while(result19.next()){

            System.out.println("movieID             movieName              avgRate");
            System.out.println(result19.getInt("movieID")+"                    "+result19.getString("movieName")+"               "+result19.getInt("avgRate"));

        }

        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +3.3);
        System.out.println("Statement:Bob rates 4 to the movies whose main actor is female");
        System.out.println("Translated SQL: insert into customerRate values (1,3,4)");
        System.out.println("Translated SQL: insert into customerRate values (1,4,4)");
        stmt.executeUpdate("insert into customerRate values (1,3,4)");
        stmt.executeUpdate("insert into customerRate values (1,4,4)");
        System.out.println("Updated tables: customerRate, movie(Trigger used)");
        /*
         ResultSet femalemovieid = stmt.executeQuery("SELECT DISTINCT cast.movieID
FROM cast
JOIN actor ON cast.actorID = actor.actorID
WHERE actor.gender = 'female'; ");
          int[] femalemovieidarray = new int[2];
          int i=0;
        while(femalemovieid.next()) {
            femalemovieidarray[i] = femalemovieid.getInt("movieID");
            i += 1;
        }
         */


        ResultSet result20 = stmt.executeQuery("select customerID,movieID,rate from customerRate where movieID = 3 or movieID = 4");
        System.out.println("----------------< customerRate >----------------");
        while(result20.next()){

            System.out.println("customerID             movieID             rate");
            System.out.println(result20.getInt("customerID")+"                       "+result20.getInt("movieID")+"                     "+result20.getInt("rate"));

        }
        ResultSet result21 = stmt.executeQuery("select movieID,movieName,avgRate from movie where movieID = 3 or movieID = 4");
        System.out.println("----------------< movie >----------------");
        while(result21.next()){

            System.out.println("movieID             movieName              avgRate");
            System.out.println(result21.getInt("movieID")+"                    "+result21.getString("movieName")+"               "+result21.getInt("avgRate"));

        }

        System.out.println(3.4);
        System.out.println("Statement:Jack rates 4 to the Action movies");
        System.out.println("Translated SQL: select distinct moveid from movieGenre where genreName = 'Action'");
        System.out.println("Translated SQL: insert into customerRate values(3, actionresults.getInt(movieID),4)");

        ResultSet actionresult = stmt.executeQuery("select distinct movieID from movieGenre where genreName = 'Action' ");
          int[] actionmovieid = new int[3];
          int i=0;
        while(actionresult.next()) {
            actionmovieid[i] = actionresult.getInt("movieID");
            i += 1;
        }
        stmt.executeUpdate("insert into customerRate values(3," + actionmovieid[0] +",4)");
        stmt.executeUpdate("insert into customerRate values(3," + actionmovieid[1] +",4)");
        stmt.executeUpdate("insert into customerRate values(3," + actionmovieid[2] +",4)");

         actionresult.close();

       // stmt.executeUpdate("insert into customerRate values(3,3,4)");
       //stmt.executeUpdate("insert into customerRate values(3,5,4)");
        //stmt.executeUpdate("insert into customerRate values(3,6,4)");
        System.out.println("Updated tables: customerRate, movie(Trigger used)");
        ResultSet result22 = stmt.executeQuery("select customerID,movieID,rate from customerRate where movieID = 5 or movieID = 6");
        System.out.println("----------------< customerRate >----------------");
        while(result22.next()){

            System.out.println("customerID             movieID             rate");
            System.out.println(result22.getInt("customerID")+"                       "+result22.getInt("movieID")+"                     "+result22.getInt("rate"));

        }
        result22.close();
        ResultSet result23 = stmt.executeQuery("select movieID,movieName,avgRate from movie where movieID = 5 or movieID = 6");
        System.out.println("----------------< movie >----------------");
        while(result23.next()){

            System.out.println("movieID             movieName              avgRate");
            System.out.println(result23.getInt("movieID")+"                    "+result23.getString("movieName")+"               "+result23.getInt("avgRate"));

        }
         result23.close();
        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +3.5);
        System.out.println("Statement:John rates 5 to the movies produced by Paramount Picture");
        System.out.println("Translated SQL: select distinct moveid from movie where publisherName = 'Paramount Pictures'");
        System.out.println("Translated SQL: insert into customerRate values(2,pararesults.getInt(movieID),5)");
        ResultSet pararesults = stmt.executeQuery("select distinct movieID from movie where publisherName = 'Paramount Pictures'");
        int[] paramountpic = new int[2];
        i=0;
        while(pararesults.next()) {
            int movieID = pararesults.getInt("movieID");
            paramountpic[i] = movieID;
            i += 1;
        }

        stmt.executeUpdate("insert into customerRate values(2," + paramountpic[0] +",5 )");
        stmt.executeUpdate("insert into customerRate values(2," + paramountpic[1] +",5 )");
        pararesults.close();

        System.out.println("Updated tables: customerRate, movie(Trigger used)");
        ResultSet result24 = stmt.executeQuery("select customerID,movieID,rate from customerRate where movieID = 2 or movieID = 4");
        System.out.println("----------------< customerRate >----------------");
        while(result24.next()){

            System.out.println("customerID             movieID             rate");
            System.out.println(result24.getInt("customerID")+"                       "+result24.getInt("movieID")+"                     "+result24.getInt("rate"));

        }

        result24.close();

        ResultSet result25 = stmt.executeQuery("select movieID,movieName,avgRate from movie where movieID = 2 or movieID = 4");
        System.out.println("----------------< movie >----------------");
        while(result25.next()){

            System.out.println("movieID             movieName              avgRate");
            System.out.println(result25.getInt("movieID")+"                    "+result25.getString("movieName")+"               "+result25.getInt("avgRate"));

        }
            result25.close();

        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +4);
        System.out.println("Statement:Select the names of the movies whose actor is dead");
        System.out.println("Translated SQL: select distinct moveid from movie where publisherName = 'Paramount Pictures'");
        System.out.println("Translated SQL: insert into customerRate values(2, pararesults.getInt(movieID) ,5)");
        System.out.println("----------------< movie >----------------");
        System.out.println("movieName");
        ResultSet deadresult = stmt.executeQuery("SELECT DISTINCT movie.movieName\n" +
                "FROM movie\n" +
                "INNER JOIN (\n" +
                "SELECT casting.movieID\n" +
                "FROM casting\n" +
                "INNER JOIN actor ON actor.actorID = casting.actorID AND actor.dateOfDeath IS NOT NULL\n" +
                "GROUP BY casting.movieID\n" +
                ") MandC ON MandC.movieID = movie.movieID");
        while(deadresult.next()) {
           System.out.println(deadresult.getString("movieName"));
        }
        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +5);
        System.out.println("Statement:Select the names of the directors who cast the same actor more than once");
        System.out.println("Translated SQL: SELECT DISTINCT D.directorName\n" +
                                 "FROM (\n" +
                                "SELECT M.directorID, C.actorID\n" +
                                "FROM make M\n" +
                                "JOIN casting C ON M.movieID = C.movieID\n" +
                                "GROUP BY M.directorID, C.actorID\n" +
                                "HAVING COUNT(DISTINCT C.movieID) > 1\n" +
                                 ") MandC\n" +
                                "JOIN director D ON D.directorID = MANDC.directorID");

        System.out.println("----------------< director >----------------");
        System.out.println("directorName");
        ResultSet directornameresult = stmt.executeQuery("SELECT DISTINCT D.directorName\n" +
                "FROM (\n" +
                "  SELECT M.directorID, C.actorID\n" +
                "  FROM make M\n" +
                "  JOIN casting C ON M.movieID = C.movieID\n" +
                "  GROUP BY M.directorID, C.actorID\n" +
                "  HAVING COUNT(DISTINCT C.movieID) > 1\n" +
                ") MandC\n" +
                "JOIN director D ON D.directorID = MANDC.directorID");
        while(directornameresult.next()) {
            System.out.println(directornameresult.getString("directorName"));
        }

        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +6);
        System.out.println("Statement:Select the name of the movies and the genres, where movies have the common genre");
        System.out.println("Translated SQL: select genreName, movieName\n" +
                "                from movie natural join movieGenre \n" +
                "                where movieGenre.genreName in (select genreName\n" +
                "                from movieGenre\n" +
                "                group by genreName\n" +
                "                having count(movieID) > 1)\n");
        System.out.println("----------------< genreName and movieName >----------------");
        System.out.println("genreName                      movieName");
        ResultSet genresandmovieresult = stmt.executeQuery("select genreName, movieName\n" +
                "from movie natural join movieGenre \n" +
                "where movieGenre.genreName in (select genreName\n" +
                "from movieGenre\n" +
                "group by genreName\n" +
                "having count(movieID) > 1)\n");
        while(genresandmovieresult.next()) {
            System.out.println(genresandmovieresult.getString("genreName") + "           " + genresandmovieresult.getString("movieName"));
        }
        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" +7);
        System.out.println("Statement:Delete the movies which were released before 2000");
        System.out.println("Translated SQL: select movieID from movie where movie.realseYear < 2001");
        int[] releaseYearid = new int[3];
        i=0;
        ResultSet releaseYear = stmt.executeQuery("select movieID from movie where movie.releaseYear < 2001");
        while(releaseYear.next()) {
            releaseYearid[i] = releaseYear.getInt("movieID");
            i += 1;
        }
        stmt.executeUpdate("delete from movie where movieID =" + releaseYearid[0]);
        stmt.executeUpdate("delete from movie where movieID =" + releaseYearid[1]);
        stmt.executeUpdate("delete from movie where movieID =" + releaseYearid[2]);
        System.out.println("----------------< movie >----------------");
        System.out.println("genreName              movieName              ReleaseDate");
        ResultSet movierow = stmt.executeQuery("select movieID, movieName, releaseYear from movie");
        while(movierow.next()) {
            System.out.println(movierow.getInt("movieID") + "                " + movierow.getString("movieName") + "                " + movierow.getInt("releaseYear"));
        }
        System.out.println("----------------< make >----------------");
        System.out.println("movieID              directorID");
        ResultSet moviemakerow = stmt.executeQuery("select movieID, directorID from make");
        while(moviemakerow.next()) {
            System.out.println(moviemakerow.getInt("movieID") + "                " + moviemakerow.getInt("directorID"));
        } // SQL query to demonstrate that all records in the 'movie' table have been deleted by the CASCADE constraint
        System.out.println("\n\n\n\n\n\n");
        System.out.println("problem:" + 8);
        System.out.println("Statement:Delete all customers and delete data from related tables");
        System.out.println("Translated SQL: delete from customer where customerID =customerid[0]");
        System.out.println("Translated SQL: delete from customer where customerID =customerid[1]");
        System.out.println("Translated SQL: delete from customer where customerID =customerid[2]");
        System.out.println("Translated SQL: delete from customer where customerID =customerid[3]");
        System.out.println("Translated SQL: delete from customer where customerID =customerid[4]");
        int[] customerid = new int[5];
        i=0;
        ResultSet customerIDset = stmt.executeQuery("select customerID from customer");
        while(customerIDset.next()) {
            customerid[i] = customerIDset.getInt("customerID");
            i += 1;
        }
        stmt.executeUpdate("delete from customer where customerID =" + customerid[0]);
        stmt.executeUpdate("delete from customer where customerID =" + customerid[1]);
        stmt.executeUpdate("delete from customer where customerID =" + customerid[2]);
        stmt.executeUpdate("delete from customer where customerID =" + customerid[3]);
        stmt.executeUpdate("delete from customer where customerID =" + customerid[4]);

        System.out.println("----------------< customer >----------------");
        System.out.println("customerID              customerName              gender");
        ResultSet customerrow = stmt.executeQuery("select customerID, customerName, gender from customer");
        while(customerrow.next()) {
            System.out.println(customerrow.getInt("customerID") + "                " + customerrow.getString("customerName") + "                " + customerrow.getString("gender"));
        }
        System.out.println("----------------< customerRate >----------------");
        System.out.println("customerID              movieID              rate");
        ResultSet customerraterow = stmt.executeQuery("select customerID, movieID, rate from customerRate");
        while(customerraterow.next()) {
            System.out.println(customerraterow.getInt("customerID") + "                " + customerraterow.getInt("movieID") + "                " + customerraterow.getInt("rate"));
        }



        //9

        System.out.println("Statement:Delete all tables and data");
        System.out.println("Translated SQL: DROP TABLE movieGenre,movieObtain,actorObtain,casting,make,customerRate,directorObtain");
        System.out.println("Translated SQL: DROP TABLE director,actor,movie,award,genre,customer");
        stmt.executeUpdate("DROP TABLE movieGenre,movieObtain,actorObtain,casting,make,customerRate,directorObtain");
        stmt.executeUpdate("DROP TABLE director,actor,movie,award,genre,customer");
        System.out.println("Drop all tables : complete");






        //connection.close()
        connection.close();




    }
}