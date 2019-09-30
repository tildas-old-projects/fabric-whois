package tf.lmao.whois.utils

// Note: This code was taken from
// https://github.com/bitcynth/spigot-whois,
// then converted using the built-in Kotlin
// conversion in IntelliJ.

import java.io.*
import java.net.Socket
import java.util.regex.Pattern

object WHOIS {

    private val regexIANAWhois = Pattern.compile("whois:[\\s]+([\\.a-z0-9\\-]+)")

    @Throws(IOException::class)
    fun queryWHOIS(host: String, port: Int, query: String): String {
        val socket = Socket(host, port)
        val input = BufferedReader(InputStreamReader(socket.getInputStream()))
        val out = BufferedOutputStream(socket.getOutputStream())

        out.write((query + "\r\n").toByteArray())
        out.flush()

        val builder = StringBuilder()
        var line: String
        line = input.readLine()
        input.lineSequence().forEach {
            builder.append(line + "\n")
        }

        return builder.toString()
    }

    @Throws(IOException::class)
    fun query(resource: String): String {
        val ianaResponse = queryWHOIS("whois.iana.org", 43, resource)
        val m = regexIANAWhois.matcher(ianaResponse)
        return if (!m.find()) return ianaResponse else queryWHOIS(m.group(1), 43, resource)
    }

}
