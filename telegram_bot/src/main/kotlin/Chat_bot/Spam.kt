package Chat_bot



import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto




open class Spam {
    open fun write(name:String,message:String,user_id:Long)
    {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
      //  val nFile = FileWriter("C:\\Users\\Хумо\\Documents\\TelegramFiles\\$name.txt")
     //   nFile.write("$user_id : $message  :: ${dateFormat.format(date)}\n" );
    //    nFile.close()
   /*     try {
            FileWriter("C:\\Users\\Хумо\\Documents\\TelegramFiles\\\$name.txt", false).use { writer ->
                // запись всей строки
                val text = "$user_id : $message  :: ${dateFormat.format(date)}\n"
                writer.write(text)
                // запись по символам
                writer.append('\n')
                writer.append('E')

                writer.flush()
            }
        } catch (ex: IOException) {

            println(ex.message)
        }*/
        var log = File("C:\\Users\\Хумо\\Documents\\TelegramFiles\\$name.txt")
        try {

            if (log.exists() === false) {
                println("We had to make a new file.")
                log.createNewFile()
            }
            val out = PrintWriter(FileWriter(log, true))
            out.append("$user_id : $message  :: ${dateFormat.format(date)}\n ")
            out.close()
        } catch (e: IOException) {
            println("COULD NOT LOG!!")
        }


    }
    fun file_scanner()
    {

        val fr = FileReader("C:\\Users\\Хумо\\Documents\\TelegramFiles\\file1.txt")
        val scan = Scanner(fr)
        var i = 1

        while (scan.hasNextLine()) {
            println(i.toString() + " : " + scan.nextLine())
            i++
        }
        fr.close()
    }

   fun delete_file(name: String)
    {
        //удаление с использованием полного пути в файлу
        var file = File("C:\\Users\\Хумо\\Documents\\TelegramFiles\\$name.txt")
        if (file.delete()) {
            println("$name.txt файл удален")
        } else
            println("Файла $name.txt не обнаружено")
    }
}