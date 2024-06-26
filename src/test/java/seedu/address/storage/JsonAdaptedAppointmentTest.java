package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Nric;

/**
 * JUnit test class for {@code JsonAdaptedAppointment}.
 */
public class JsonAdaptedAppointmentTest {
    private static final String INVALID_NRIC_1 = "G1234A";
    private static final String INVALID_NRIC_2 = "S123456A";
    private static final String INVALID_DATE = "2023-02-31 11:0";
    private static final String INVALID_APPOINTMENT_ID = "a9568782";

    private static final String VALID_NRIC_1 = APPOINTMENT_1.getPatientNric().toString();
    private static final String VALID_NRIC_2 = APPOINTMENT_1.getDoctorNric().toString();
    private static final String VALID_DATE_A = APPOINTMENT_1.getAppointmentDateTime().toString();
    private static final String VALID_DATE_B = APPOINTMENT_1.getAppointmentDateTime().toString();

    /**
     * Tests the conversion of an appointment with invalid patient and doctor NRIC to a model type.
     */
    @Test
    public void toModelType_invalidPatientDoctorNric_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(INVALID_NRIC_1, INVALID_NRIC_2,
                VALID_DATE_A);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    /**
     * Tests the conversion of an appointment with invalid patient NRIC to a model type.
     */
    @Test
    public void toModelType_invalidPatientNric_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_1, INVALID_NRIC_2,
                VALID_DATE_A);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    /**
     * Tests the conversion of an appointment with missing doctor NRIC to a model type.
     */
    @Test
    public void toModelType_missingDoctorNric_throwsMissingFieldException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(null, VALID_NRIC_2,
                VALID_DATE_A);
        String expectedMessage = String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT, "Nric");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    /**
     * Tests the conversion of an appointment with missing patient NRIC to a model type.
     */
    @Test
    public void toModelType_missingPatientNric_throwsMissingFieldException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_1, null,
                VALID_DATE_A);
        String expectedMessage = String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT, "Nric");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    /**
     * Tests the conversion of an appointment with missing appointment dateTime to a model type.
     */
    @Test
    public void toModelType_missingAppointmentDateTime_throwsMissingFieldException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_1, VALID_NRIC_2,
                null);
        String expectedMessage =
                String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT, "AppointmentDateTime");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    /**
     * Tests the conversion of an appointment with invalid doctor NRIC to a model type.
     */
    @Test
    public void toModelType_invalidDoctorNric_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_1, INVALID_NRIC_2, VALID_DATE_A);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    /**
     * Tests the conversion of an appointment with invalid appointment dateTime to a model type.
     */
    @Test
    public void toModelType_invalidAppointmentDateTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_1, VALID_NRIC_2,
                INVALID_DATE);
        String expectedMessage = AppointmentDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    /**
     * Tests the conversion of a valid appointment with valid details to a model type.
     * The method should return an {@code Appointment} object that matches the predefined {@code APPOINTMENT_1}.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_2, VALID_NRIC_1,
                VALID_DATE_A);
        assertEquals(APPOINTMENT_1, appointment.toModelType());
    }

    /**
     * Tests the conversion of an appointment with different details to a model type.
     * The method should return an {@code Appointment} object that is different
     * from the predefined {@code APPOINTMENT_1}.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toModelType_differentAppointmentDetails_returnsDifferentAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_1, VALID_NRIC_2, VALID_DATE_A);
        assertFalse(APPOINTMENT_1.equals(appointment.toModelType()));
    }

    /**
     * Tests the conversion of a valid {@code Appointment} object to a {@code JsonAdaptedAppointment} object.
     * The method should return a {@code JsonAdaptedAppointment} object that is
     * equal to the expected JSON-adapted appointment.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toJsonAdapatedAppointment_validAppointmentObject_returnsValidJsonAdaptedAppointment() throws Exception {
        JsonAdaptedAppointment jsonAdaptedAppointment = new JsonAdaptedAppointment(APPOINTMENT_1);
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_2, VALID_NRIC_1, VALID_DATE_B);
        assertEquals(jsonAdaptedAppointment, appointment);
    }

    /**
     * Tests the conversion of a different {@code Appointment} object to a {@code JsonAdaptedAppointment} object.
     * The method should return a {@code JsonAdaptedAppointment} object that is different
     * from the expected JSON-adapted appointment.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toJsonAdapatedAppointment_differentAppointmentObject_returnsDifferentJsonAdaptedAppointment()
            throws Exception {
        JsonAdaptedAppointment jsonAdaptedAppointment = new JsonAdaptedAppointment(APPOINTMENT_2);
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC_2, VALID_NRIC_1, VALID_DATE_A);
        assertFalse(jsonAdaptedAppointment.equals(appointment));
    }

    /**
     * Tests the comparison of a {@code JsonAdaptedAppointment} object with itself.
     * The method should return true if the objects are the same instance.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toJsonAdapatedAppointment_sameAppointmentObject_returnsSameJsonAdaptedAppointment() throws Exception {
        JsonAdaptedAppointment jsonAdaptedAppointment = new JsonAdaptedAppointment(APPOINTMENT_2);
        assert(jsonAdaptedAppointment.equals(jsonAdaptedAppointment));
    }

    /**
     * Tests the comparison of a {@code JsonAdaptedAppointment} object with a different type of object.
     * The method should return false as the objects are of different types.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void toJsonAdapatedAppointment_differentObjectType_returnsFalse() throws Exception {
        JsonAdaptedAppointment jsonAdaptedAppointment = new JsonAdaptedAppointment(APPOINTMENT_2);
        assertFalse(jsonAdaptedAppointment.equals(APPOINTMENT_2));
    }
}
