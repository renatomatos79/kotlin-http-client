import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

fun getServices(json: String): List<ServiceType>{
    val list = mutableListOf<ServiceType>()
    var array = org.json.JSONArray(json)
    for (i in 0..array.length()-1)
    {
        val item = array.getJSONObject(i)
        val id = item.optInt("id")
        val name = item.optString("name")
        val value = item.optDouble("value")
        val service = ServiceType(id, name, value)
        list.add(service)
    }
    return list
}

fun getServicesFromGSON(json: String): List<ServiceType>{
    val type = object: TypeToken<List<ServiceType>>(){}.type
    return Gson().fromJson<List<ServiceType>>(json, type)
}


fun downloadJSON(): String{
    return URL("http://localhost:3000/services").readText();
}

fun main(args: Array<String>) {
    var json = downloadJSON()

    val list1 = getServices(json)
    for (item in list1)
    {
        println("${item.id} - ${item.name}")
    }

    val list2 = getServicesFromGSON(json)
    for (item in list2)
    {
        println("${item.id} - ${item.name}")
    }



}