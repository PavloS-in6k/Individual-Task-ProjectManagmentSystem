package DB;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
    private static IDatabaseTester databaseTester = null;

    private void dropDB() {
    }

    public void setUpDB() throws Exception {
        if (databaseTester == null) {
            databaseTester = new JdbcDatabaseTester(org.hsqldb.jdbcDriver.class
                    .getName(), "jdbc:hsqldb:file:ProjectDB/ProjectDB", "sa", "");

            PreparedStatement statement = databaseTester.getConnection()
                    .getConnection().prepareStatement("DROP SCHEMA PUBLIC CASCADE");
            statement.execute();
            statement.close();


            createTablesSinceDbUnitDoesNot(databaseTester.getConnection().getConnection());
            String inputXml = getStringDBfromXml();
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(new StringReader(inputXml));
            databaseTester.setDataSet(dataSet);
            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
            databaseTester.onSetup();
        }
    }

    private static void createTablesSinceDbUnitDoesNot(Connection connection)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE Employees" +
                "(ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, Name VARCHAR(255), " +
                "Surname VARCHAR(255))");
        statement.execute();
        statement.close();

        statement = connection.prepareStatement("CREATE TABLE Projects" +
                "(ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, ProjectName VARCHAR(255))");
        statement.execute();
        statement.close();

        statement = connection.prepareStatement("CREATE TABLE Technologies" +
                "(ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, Name VARCHAR(255))");
        statement.execute();
        statement.close();

        statement = connection.prepareStatement("CREATE TABLE Assigments" +
                "(ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                "ProjectID INT, EmployeeID INT, " +
                "FOREIGN KEY(ProjectID) REFERENCES Projects(ID), " +
                "FOREIGN KEY(EmployeeID) REFERENCES Employees(ID))");
        statement.execute();
        statement.close();

        statement = connection.prepareStatement("CREATE TABLE ProjectTechnologies" +
                "(ProjectID INT,  TechnologyID INT, " +
                "FOREIGN KEY(ProjectID) REFERENCES Projects(ID), " +
                "FOREIGN KEY(TechnologyID) REFERENCES Technologies(ID))");
        statement.execute();
        statement.close();

        statement = connection.prepareStatement("CREATE TABLE EmployeeTechnologies" +
                "(EmployeeID INT, TechnologyID INT, " +
                "FOREIGN KEY(EmployeeID) REFERENCES Employees(ID), " +
                "FOREIGN KEY(TechnologyID) REFERENCES Technologies(ID))");
        statement.execute();
        statement.close();


        statement = connection.prepareStatement("CREATE TABLE AssigmentTechnologies" +
                "(AssigmentID INT, TechnologyID INT, " +
                "FOREIGN KEY(TechnologyID) REFERENCES Technologies(ID), " +
                "FOREIGN KEY(AssigmentID) REFERENCES Assigments(ID))");
        statement.execute();
        statement.close();
    }


    protected String getStringDBfromXml() throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;

        Document doc = getXml();
        transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        return writer.getBuffer().toString();
    }

    private Document getXml() throws ParserConfigurationException, SAXException, IOException {
        //File xmlFile = new File("/WEB_INF/classes/DB.xml");
        File xmlFile = new File(this.getClass().getClassLoader().getResource("/DB.xml").getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(xmlFile);
    }

    public static void cleanUp() throws Exception {
        databaseTester.onTearDown();
    }
}
