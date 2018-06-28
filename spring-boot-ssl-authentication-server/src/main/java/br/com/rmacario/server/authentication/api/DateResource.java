package br.com.rmacario.authentication.api;

import java.security.Principal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateResource {

	private final Calendar cal = GregorianCalendar.getInstance();
	
	@GetMapping("/")
	public String getLastDayCurrentMonth(Principal principal) {
		return principal.getName() + " o último dia deste mês é " + String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
}
