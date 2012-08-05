package glay.ash.madoka;

import android.app.Activity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button button;
	private AnimationSet animation;
	private View view;
	
	private boolean isAnimate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 適当な正方形のviewを画面中央に配置
		 view = (View) findViewById(R.id.view1);

		 //X軸の移動アニメーション
		Animation transX = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 3.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		transX.setInterpolator(new SinInterpolator());
		transX.setDuration(2000);

		//Y軸の移動アニメーション
		Animation transY = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 3.0f);
		transY.setInterpolator(new CosInterpolator());
		transY.setDuration(2000);

		//X軸とY軸のアニメーションを合成
		animation = new AnimationSet(false);
		animation.addAnimation(transX);
		animation.addAnimation(transY);
		animation.setFillAfter(true);

		// 適当なボタン
		button = (Button) findViewById(R.id.button1);
		button.setText("回転させる");
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(isAnimate){
			isAnimate = false;
		}else{
			view.startAnimation(animation);
			isAnimate = true;
		}
	}

	class SinInterpolator implements Interpolator {
		private float restoreInput;
		
		@Override
		public float getInterpolation(float input) {
			if(isAnimate)
				restoreInput = input;
			
			return FloatMath.sin((float) (restoreInput * 2 * Math.PI));
		}
	}

	class CosInterpolator implements Interpolator {
		private float restoreInput;
		
		@Override
		public float getInterpolation(float input) {
			if(isAnimate)
				restoreInput = input;
			
			return  FloatMath.cos((float) (restoreInput * 2 * Math.PI));
		}
	}
}
