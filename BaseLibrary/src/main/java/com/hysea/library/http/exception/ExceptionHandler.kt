import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import com.hysea.library.R
import com.hysea.library.http.exception.ApiException
import com.hysea.library.http.exception.ServerException
import com.hysea.library.utils.LogUtils
import com.hysea.library.utils.TAG
import com.hysea.library.utils.getString
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * 异常处理
 * Created by hysea on 2018/6/28.
 */
object ExceptionHandler {
    const val UN_KNOWN_ERROR = 2000//未知错误
    const val PARSE_ERROR = 2001//解析错误
    const val CONNECT_ERROR = 2003//网络连接错误
    const val TIME_OUT_ERROR = 2004//网络连接超时
    const val NOT_NETWORK_ERROR = 2005 // 无网络状态

    fun handleException(e: Throwable): ApiException = when (e) {
        is HttpException -> { // http异常
            val ex = ApiException(e.code(), e)
            LogUtils.e(TAG(), "HttpException errorCode = ${e.code()}")
            ex.msg = getString(R.string.http_error)
            ex
        }
        is ServerException -> { // 服务器返回异常
            val ex = ApiException(e.code, e)
            ex.msg = e.msg
            ex
        }
        is JsonParseException,
        is JSONException,
        is ParseException,
        is MalformedJsonException -> { // 解析数据异常
            val ex = ApiException(PARSE_ERROR, e)
            ex.msg = getString(R.string.parse_error)
            ex
        }
        is ConnectException -> { // 网络连接异常
            val ex = ApiException(CONNECT_ERROR, e)
            ex.msg = getString(R.string.connect_error)
            ex
        }
        is SocketTimeoutException -> { // 网络超时异常
            val ex = ApiException(TIME_OUT_ERROR, e)
            ex.msg = getString(R.string.connect_timeout)
            ex
        }
        else -> { // 其他异常
            val ex = ApiException(UN_KNOWN_ERROR, e)
            ex.msg = getString(R.string.server_error)
            ex
        }
    }
}