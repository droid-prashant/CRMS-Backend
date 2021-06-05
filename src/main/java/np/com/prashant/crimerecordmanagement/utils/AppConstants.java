package np.com.prashant.crimerecordmanagement.utils;

public class AppConstants {
	public static final String NOTES_URI = "/notes";
	public static final String POSTS_JSON_PARAM = "postsJson";
	public static final String NOTES_JSON_PARAM = "notesJson";
	public static final String DISCUSSION_JSON_PARAM = "discussionJson";
	public static final String DISCUSSION_FILE_PARAM = "file";
	public static final String NOTES_FILE_PARAM = "file";
	public static final String PROFILE_FILE_PARAM = "profile";
	public static final String FILE_SEPERATOR = "_";
	public static final String DOWNLOAD_PATH_FOR_PROFILE = "/api/profilePicture/downloadFile/";
	public static final String DOWNLOAD_PATH_FOR_DISCUSSION = "/api/discussionforum/downloadFile/";
	public static final String DOWNLOAD_PATH_FOR_FILE = "/api/notes/downloadFile/";
	public static final String DOWNLOAD_PATH_FOR_POST = "/api/posts/downloadFile/";
	public static final String PATH_FOR_OTP_GENERATE="/api/email/generateOtp/";
	public static final String PATH_FOR_OTP_VALIDATION = "/api/forgotPassword/validateOtp/";
	public static final String PATH_FOR_PASSWORD_CHANGE = "/api/forgotPassword/changePassword/";
	public static final String PATH_FOR_EMAIL_VERIFICATION = "/api/email/emailVerification/";
	public static final String DOWNLOAD_URI = "/downloadFile/{fileName:.+}";
	public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
	public static final String FILE_DOWNLOAD_HTTP_HEADER = "attachment; filename=\"%s\"";
	public static final String FILE_PROPERTIES_PREFIX = "file";
	public static final String FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND = "Could not create the directory where the uploaded files will be stored";
	public static final String INVALID_FILE_PATH_NAME = "Sorry! Filename contains invalid path sequence";
	public static final String FILE_NOT_FOUND = "There is no file with such name ";
	public static final String FILE_STORAGE_EXCEPTION = "Could not store file %s !! Please try again!";
	public static final CharSequence INVALID_FILE_DELIMITER = "..";
	public static final String INVALID_FILE_DIMENSIONS = "Invalid file dimensions. File dimension should note be more than 300 X 300";
	public static final String INVALID_FILE_FORMAT = "Only PNG, JPEG and JPG images are allowed";
	public static final String PNG_FILE_FORMAT = ".png";
	public static final String JPEG_FILE_FORMAT = ".jpeg";
	public static final String JPG_FILE_FORMAT = ".jpg";
		public static final String CAPITAL_PNG_FILE_FORMAT = ".PNG";
	public static final String CAPITAL_JPEG_FILE_FORMAT = ".JPEG";
	public static final String CAPITAL_JPG_FILE_FORMAT = ".JPG";
	public static final String DOWNLOAD_PATH_FOR_CRIMINAL_PICTURE="/api/criminalPicture/downloadFile/";
	public static final String DOWNLOAD_PATH_FOR_POLICESTAFF_PICTURE="api/policeStaffPicture/downloadFile";
	public static final String DOWNLOAD_PATH_FOR_EVIDENCE_RECORD="api/EvidenceRecord/downloadFile";

}
