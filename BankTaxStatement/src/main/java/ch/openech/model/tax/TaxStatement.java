package ch.openech.model.tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.minimalj.model.Keys;
import org.minimalj.model.annotation.NotEmpty;
import org.minimalj.model.annotation.Size;
import org.minimalj.util.mock.Mocking;

import ch.openech.model.EchFormats;
import ch.openech.model.common.Canton;

/**
 * Steuerauszug
 */
public class TaxStatement implements Mocking {
	public static final TaxStatement $ = Keys.of(TaxStatement.class);
	
	public Object id;
	
	/*
// http://www.datypic.com/sc/xsd/t-xsd_ID.html
 * 
The type xsd:ID is used for an attribute that uniquely identifies an element in an XML document. An xsd:ID value must be an NCName. This means that it must start with a letter or underscore, and can only contain letters, digits, underscores, hyphens, and periods.

xsd:ID carries several additional constraints:

Their values must be unique within an XML instance, regardless of the attribute's name or its element name.
A complex type cannot include more than one attribute of type xsd:ID, or any type derived from xsd:ID.
xsd:ID attributes cannot have default or fixed values specified.
	 */
	@Size(255)
	public String statementId;
	
	@NotEmpty
	public Integer minorVersion;	// Die Versionsnummer des Steuerauszugs
	
	@NotEmpty
	public final Instituion instituion = new Instituion();
	
	@NotEmpty
	public final List<Client> client = new ArrayList<>();
	
	public final List<AccompanyingLetter> accompanyingLetter = new ArrayList<>();

	public final ListOfBankAccounts listOfBankAccounts = new ListOfBankAccounts();

	public final ListOfBankAccounts listOfLiabilities = new ListOfBankAccounts();

	public final ListOfExpenses listOfExpenses = new ListOfExpenses();

	public final ListOfSecurities listOfSecurities = new ListOfSecurities();

 	public final ListOfLumpSumTaxCredit listOfLumpSumTaxCredit = new ListOfLumpSumTaxCredit();

	@NotEmpty @Size(Size.TIME_WITH_SECONDS)
	public LocalDateTime creationDate = LocalDateTime.now();
	
	@NotEmpty
	public Integer taxPeriod;
	
	@NotEmpty
	public LocalDate periodFrom, periodTo;
	
	@Size(EchFormats.countryIdISO2)
	public String country = "CH";
	
	// @NotEmpty
	public Canton canton;
	
	@NotEmpty @Size(EchFormats.currencyIdISO3)
	public String currency = "CHF";

	@NotEmpty
	public BigDecimal totalTaxValue;
	@NotEmpty
	public BigDecimal totalGrossRevenueA, totalGrossRevenueACanton;
	@NotEmpty
	public BigDecimal totalGrossRevenueB, totalGrossRevenueBCanton;
	
	@NotEmpty
	public BigDecimal totalWithHoldingTaxClaim; // in Schema ohne "Claim"
	
	@Override
	public void mock() {
		statementId = "mockId1234";
		minorVersion = 1;
		instituion.uid.mock();
		taxPeriod = 2014;
		periodFrom = LocalDate.of(2014, 1, 1);
		periodTo = LocalDate.of(2014, 12, 31);
		totalGrossRevenueA = BigDecimal.valueOf(123.45);
		totalGrossRevenueACanton = BigDecimal.valueOf(120.0);
		totalGrossRevenueB = BigDecimal.valueOf(234.5);
		totalGrossRevenueBCanton = BigDecimal.valueOf(230.0);
		totalTaxValue = BigDecimal.valueOf(345.0);
		totalWithHoldingTaxClaim = BigDecimal.valueOf(340.0);
		Client client = new Client();
		client.clientNumber = "42";
		client.firstName = "Felix";
		client.lastName = "Muster";
		this.client.add(client);
//		accompanyingLetter.add(new AccompanyingLetter());

	}

}
