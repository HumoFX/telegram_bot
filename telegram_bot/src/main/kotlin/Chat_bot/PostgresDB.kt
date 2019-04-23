package Chat_bot

import java.sql.*

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import java.sql.ResultSet
import org.glassfish.hk2.utilities.reflection.Pretty.array






open class PostgreSQLJDBC {


    open fun CREATE() {
            var c: Connection? = null
            var stmt: Statement? = null
            try {
                Class.forName("org.postgresql.Driver")
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
                println("Opened database successfully")

                stmt = c!!.createStatement()
                val sql = "CREATE TABLE CHATBOT2_SEARCH" +
                        "(ID               INT    NOT NULL,"  +
                        " GENDER           TEXT   NOT NULL )"
                stmt!!.executeUpdate(sql)
                stmt.close()
                c.close()
            } catch (e: Exception) {
                System.err.println(e.javaClass.name + ": " + e.message)
                System.exit(0)
            }

            println("Table created successfully")
        }
    open fun CREATE1() {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            println("Opened database successfully")

            stmt = c!!.createStatement()
            val sql = "CREATE TABLE CHATBOT2_CONNECTED" +
                    "(ID               INT    NOT NULL,"  +
                    " GENDER           TEXT   NOT NULL )"
            stmt!!.executeUpdate(sql)
            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

        println("Table created successfully")
    }

    open fun write(user_id: Long, text: String,position: Int,profilename:String,language:Int) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            var sql: String
            if (text == "male") {
                sql = "INSERT INTO CHATBOT2 (ID,GENDER,POSITION,AGE,LANGUAGE,SPAMLIST,PREMIUM,TEAM,REFERAL,USERNAME,PROFILENAME,CITY,COUNTRY) " + "VALUES ($user_id,'male',$position,0,$language,0,0,0,0,'PUSTO','$profilename','PUSTO','PUSTO');"
            } else {
                sql = "INSERT INTO CHATBOT2 (ID,GENDER,POSITION,AGE,LANGUAGE,SPAMLIST,PREMIUM,TEAM,REFERAL,USERNAME,PROFILENAME,CITY,COUNTRY) " + "VALUES ($user_id,'female',$position,0,$language,0,0,0,0,'PUSTO','$profilename','PUSTO','PUSTO');"
            }
            stmt.executeUpdate(sql)

            stmt.close()
            c.commit()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

    }

    open fun read(): Array<Array<String>> {
        var c: Connection? = null
        var stmt: Statement? = null
        var i =0
        var test=read1(i)
        var temp: Array<Array<String>> = Array(test, { Array(14, {"0"}) })

        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            val rs = stmt!!.executeQuery("SELECT * FROM CHATBOT2;")

            var t = 0
            if(test-1==0)
            {
                test=0
            }
            else
            {
                while (rs.next()) {
                    val id = rs.getInt("id")
                    val gender = rs.getString("gender")
                    val position = rs.getInt("position")
                    val profilename = rs.getString("profilename")
                    val language = rs.getInt("language")
                    temp[t][0] = id.toString()
                    temp[t][1] = gender
                    temp[t][3] = position.toString()
                    temp[t][4] = profilename
                    temp[t][5] = language.toString()
                    println("pos: ${temp[t][3]}")
                    t++
                }
            }
            rs.close()
            stmt.close()
            c.close()

            temp[0][2]=test.toString()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            //System.exit(0)
        }

        println("Operation READ done successfully")
        return temp
    }
    fun read1(i:Int): Int {
        var c: Connection? = null
        var stmt: Statement? = null
        var test =i
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false

            stmt = c.createStatement()
            val rs = stmt!!.executeQuery("SELECT * FROM CHATBOT2;")
            while (rs.next()) {
                test++

            }
            if(test==0){test=1}

            rs.close()
            stmt.close()
            c.close()

        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            //System.exit(0)
        }
        return test
    }



    open fun update(user_id: Long,position: Int) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false
            println("Opened database successfully")

            stmt = c.createStatement()
            val sql = "UPDATE CHATBOT2 set POSITION = $position where ID=$user_id;"
            stmt!!.executeUpdate(sql)
            c.commit()
            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

        println("Operation Update done successfully")
    }

    open fun update_emoji(user_id: Long,profilename: String) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false
            println("Opened database successfully")

            stmt = c.createStatement()
            val sql = "UPDATE CHATBOT2 set PROFILENAME = '$profilename' where ID=$user_id;"
            stmt!!.executeUpdate(sql)
            c.commit()
            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

        println("Operation Update done successfully")
    }

    open fun update_lang(user_id: Long,language: Int) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false
            println("Opened database successfully")

            stmt = c.createStatement()
            val sql = "UPDATE CHATBOT2 set LANGUAGE = $language where ID=$user_id;"
            stmt!!.executeUpdate(sql)
            c.commit()
            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

        println("Operation Update done successfully")
    }



    open fun write_SEARCH(user_id: Long, text: String) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            var sql: String
            if (text == "male") {
                sql = "INSERT INTO CHATBOT2_SEARCH (ID,GENDER) " + "VALUES ($user_id,'male');"
            } else {
                sql = "INSERT INTO CHATBOT2_SEARCH (ID,GENDER) " + "VALUES ($user_id,'female');"
            }
            stmt.executeUpdate(sql)

            stmt.close()
            c.commit()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

    }





    open fun read_search(): Array<Array<String>> {
        var c: Connection? = null
        var stmt: Statement? = null
        var i =0
        var test=read1_search(i)
        var temp: Array<Array<String>> = Array(test, { Array(3, {"0"}) })

        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            val rs = stmt!!.executeQuery("SELECT * FROM CHATBOT2_SEARCH;")

            var t = 0
            if(test-1==0)
            {
                test=0
            }
            else
            {
                while (rs.next()) {
                    val id = rs.getInt("id")
                    val gender = rs.getString("gender")
                    temp[t][0] = id.toString()
                    temp[t][1] = gender

                    t++
                }
            }
            rs.close()
            stmt.close()
            c.close()

            temp[0][2]=test.toString()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            //System.exit(0)
        }

        println("Operation READ done successfully")
        return temp
    }
    fun read1_search(i:Int): Int {
        var c: Connection? = null
        var stmt: Statement? = null
        var test =i
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false

            stmt = c.createStatement()
            val rs = stmt!!.executeQuery("SELECT * FROM CHATBOT2_SEARCH;")
            while (rs.next()) {
                test++

            }
            if(test==0){test=1}

            rs.close()
            stmt.close()
            c.close()

        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            //System.exit(0)
        }
        return test
    }

    open fun delete_SEARCH(user_id: Long) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            val sql = "DELETE from CHATBOT2_SEARCH where ID = $user_id;"
            stmt!!.executeUpdate(sql)
            c.commit()

            /* val rs = stmt.executeQuery("SELECT * FROM CHATBOT;")
             while (rs.next()) {
                 val id = rs.getInt("id")

             }*/
            //rs.close()

            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }
    }



    open fun read_conn(): Array<Array<String>> {
        var c: Connection? = null
        var stmt: Statement? = null
        var i =0
        var test=read1_conn(i)
        var temp: Array<Array<String>> = Array(test, { Array(3, {"0"}) })

        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            val rs = stmt!!.executeQuery("SELECT * FROM CHATBOT2_CONNECTED;")

            var t = 0
            if(test-1==0)
            {
                test=0
            }
            else
            {
                while (rs.next()) {
                    val id = rs.getInt("id")
                    val gender = rs.getString("gender")
                    temp[t][0] = id.toString()
                    temp[t][1] = gender

                    t++
                }
            }
            rs.close()
            stmt.close()
            c.close()

            temp[0][2]=test.toString()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            //System.exit(0)
        }

        println("Operation READ done successfully")
        return temp
    }
    fun read1_conn(i:Int): Int {
        var c: Connection? = null
        var stmt: Statement? = null
        var test =i
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false

            stmt = c.createStatement()
            val rs = stmt!!.executeQuery("SELECT * FROM CHATBOT2_CONNECTED;")
            while (rs.next()) {
                test++

            }
            if(test==0){test=1}

            rs.close()
            stmt.close()
            c.close()

        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            //System.exit(0)
        }
        return test
    }

    open fun write_conn(user_id: Long, text: String) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            var sql: String
            if (text == "male") {
                sql = "INSERT INTO CHATBOT2_CONNECTED (ID,GENDER) " + "VALUES ($user_id,'male');"
            } else {
                sql = "INSERT INTO CHATBOT2_CONNECTED (ID,GENDER) " + "VALUES ($user_id,'female');"
            }
            stmt.executeUpdate(sql)

            stmt.close()
            c.commit()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }

    }





    open fun delete_connected(user_id: Long) {
        var c: Connection? = null
        var stmt: Statement? = null
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")
            c!!.autoCommit = false


            stmt = c.createStatement()
            val sql = "DELETE from CHATBOT2_CONNECTED where ID = $user_id;"
            stmt!!.executeUpdate(sql)
            c.commit()

            /* val rs = stmt.executeQuery("SELECT * FROM CHATBOT;")
             while (rs.next()) {
                 val id = rs.getInt("id")

             }*/
            //rs.close()

            stmt.close()
            c.close()
        } catch (e: Exception) {
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }
    }

        fun write(user_id: Long) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


}



//.getConnection("jdbc:postgresql://localhost:5432/ChatBotDB", "postgres", "Q1w2e3r4t5")

/*
val sql = "CREATE TABLE CHATBOT " +
        "(ID               INT    NOT NULL," +
        " GENDER           TEXT    NOT NULL )"*/
