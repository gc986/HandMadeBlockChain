import java.lang.Exception
import java.util.*

private val ERR = "ERR#"

fun main(){
    val chains = ArrayList<Block>()

    println("Start program:: ${Date()}")

    var lastBlock = Block("-1",
        "0",
        0,
        "",
        "")

    val firstHash = makeHashForBlock(lastBlock)
    if (firstHash == ERR)
        throw Exception("ERROR: zero hash not found")

    lastBlock.hash = firstHash
    chains.add(lastBlock)
    println(lastBlock.toString())
    
    for(i in 1..25){
        val block = Block(lastBlock.to, "$i", 0, "", lastBlock.hash)
        val nextHash = makeHashForBlock(block)
        if (nextHash == ERR)
            throw Exception("ERROR: $i hash not found")
        block.hash = nextHash
        chains.add(block)
        lastBlock = block
        println("$i -----------------------")
        println(block.toString())
    }

    // compromat

    println()
    println("Find compromat")
    chains[10].to = "101"

    for (i in 0 until chains.size){
        val block = chains[i]
        val oldHash = block.hash
        block.hash = ""
        val checkHash = makeHashForBlock(block)
        block.hash = oldHash
        if(oldHash!=checkHash){
            println("Block $i is BAD!")
            println(block.toString())
            throw Exception("Not bad ))")
        }
    }

    println("End of program :: ${Date()}")
}

fun makeHashForBlock(block: Block): String{
    val test = HashMaker()

    for (i in 1..1000000000){
        block.nounce = i
        val str = test.makeSHA256(block)
        if (str[0]=='0' &&
            str[1]=='0' &&
            str[2]=='0' &&
            str[3]=='0')
            return str
    }

    return ERR
}