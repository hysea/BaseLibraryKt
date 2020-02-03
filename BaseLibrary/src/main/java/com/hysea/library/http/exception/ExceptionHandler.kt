import android.content.Context
import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import com.hysea.library.base.BaseApp
import com.hysea.library.http.exception.ApiException
import com.hysea.library.utils.isConnected
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 异常处理
 * Created by hysea on 2018/6/28.
 */
object ExceptionHandler {
    const val OTHER_ERROR = 2000//未知错误
    const val PARSE_ERROR = 2001//解析错误
    const val CONNECT_ERROR = 2003//网络连接错误
    const val TIME_OUT_ERROR = 2004//网络连接超时
    const val NOT_NETWORK_ERROR = 2005 // 无网络状态

    private val context: Context by lazy {
        BaseApp.instance
    }

    fun handleException(e: Throwable): ApiException = when (e) {

        // http异常
        is HttpException -> ApiException(e.code(), e.message())

        // 解析数据异常
        is JsonParseException,
        is JSONException,
        is ParseException,
        is MalformedJsonException -> ApiException(PARSE_ERROR, e.message)

        // 网络连接异常
        is ConnectException -> ApiException(CONNECT_ERROR, e.message)

        // 网络超时异常
        is UnknownHostException,
        is SocketTimeoutException -> ApiException(TIME_OUT_ERROR, e.message)

        // 其他异常
        else -> ApiException(OTHER_ERROR, e.message)
    }
}