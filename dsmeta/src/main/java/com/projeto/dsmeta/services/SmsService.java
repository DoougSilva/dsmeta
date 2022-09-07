package com.projeto.dsmeta.services;

import com.projeto.dsmeta.entities.Sale;
import com.projeto.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	private final SaleRepository saleRepository;

	public SmsService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	public void sendSms(Long id) {

		Sale sale = saleRepository.findById(id).get();

		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		String msg = "The Seller " + sale.getSellerName() + " was featured in " + date
				+ " with a total of $ " + String.format("%.2f", sale.getAmount()) + "!";

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}
}