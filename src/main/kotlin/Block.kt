import java.io.Serializable

class Block(val from:String,
            var to: String,
            var nounce: Int,
            var hash: String,
            var parentHash: String): Serializable {

    override fun toString(): String{
        return "from $from, to $to, nounce = $nounce \n hash: $hash"
    }

}