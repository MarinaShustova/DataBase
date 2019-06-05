package theater.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Datetime format: yyyy-[m]m-[d]d hh:mm:ss")
class WrongDataFormatException(message: String?) : Exception(message)