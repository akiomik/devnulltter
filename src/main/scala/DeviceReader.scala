package devnulltter

import scala.io._

class DeviceReader(filePath: String) {
    val source = Source.fromFile(filePath)

    // ファイルから読み込む
    def read(): String = {
        var text = ""
        for (line <- source.getLines) {
            text += line
        }

        return text
    }

    // TODO
    // これどうにかしたいけど…… 
    def close() {
        source.close
    }
}
