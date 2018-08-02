package id.tiregdev.atentik.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class CekToken
{
	SharedPreferences sharedPreferences;

	public String Cek(Context context)
	{
		sharedPreferences = context.getSharedPreferences("Token", Context.MODE_PRIVATE);
		String tkn = sharedPreferences.getString("Token", null);
		return tkn;
	}
}
