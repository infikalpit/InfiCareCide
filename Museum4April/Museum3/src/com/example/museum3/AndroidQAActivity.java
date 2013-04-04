package com.example.museum3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidQAActivity extends Activity


	{
	
	
		// TODO: Externalize string-array
	String wheelMenu1[] = new String[]{"Portugese", "English"};
   


		// Wheel scrolled flag
		private boolean wheelScrolled = false;

		private TextView text;
		
		Context  ctx;

		@Override
		public void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.main);
				ctx = AndroidQAActivity.this;
				initWheel1(R.id.p1);
				

				
				text = (TextView) this.findViewById(R.id.result);
			}

		// Wheel scrolled listener
		OnWheelScrollListener scrolledListener = new OnWheelScrollListener()
			{
				public void onScrollStarts(WheelView wheel)
					{
						wheelScrolled = true;
					}

				public void onScrollEnds(WheelView wheel)
					{
						wheelScrolled = false;
						updateStatus();
					}

				@Override
				public void onScrollingStarted(WheelView wheel) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onScrollingFinished(WheelView wheel) {
					// TODO Auto-generated method stub
			String	a;
				//a=wheel.getCurrentItemId();
				
			//	a=wheelMenu1[getWheel(R.id.p1).getCurrentItemPosition()];
					a=wheelMenu1[getWheel(R.id.p1).getCurrentItem()];
					
					if(a=="Portugese")
					{
						  int i=1;
						  Intent intent = new Intent(getApplicationContext(),Museum_Activity.class);
			    		  intent.putExtra("selected", i);
			    		  startActivity(intent);  
						
					}
					else if(a=="English")
					{
						int i=2;
						  Intent intent = new Intent(getApplicationContext(),Museum_Activity.class);
			    		  intent.putExtra("selected", i);
			    		  startActivity(intent);  
					}
					
					
					//Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_LONG).show();
				}
			};

		// Wheel changed listener
		private final OnWheelChangedListener changedListener = new OnWheelChangedListener()
			{
				public void onChanged(WheelView wheel, int oldValue, int newValue)
					{
						if (!wheelScrolled)
							{
								updateStatus();
							}
					}
			};

		/**
		 * Updates entered PIN status
		 */
		private void updateStatus()
			{
				

				text.setText(wheelMenu1[getWheel(R.id.p1).getCurrentItem()]);
				
				
				
			}

		/**
		 * Initializes wheel
		 * 
		 * @param id
		 *          the wheel widget Id
		 */

		private void initWheel1(int id)
			{
				WheelView wheel = (WheelView) findViewById(id);
				wheel.setViewAdapter(new ArrayWheelAdapter(ctx,wheelMenu1));
				wheel.setVisibleItems(2);
				wheel.setCurrentItem(0);
				wheel.addChangingListener(changedListener);
				wheel.addScrollingListener(scrolledListener);
			}

		

	

		/**
		 * Returns wheel by Id
		 * 
		 * @param id
		 *          the wheel Id
		 * @return the wheel with passed Id
		 */
		private WheelView getWheel(int id)
			{
				return (WheelView) findViewById(id);
			}

		/**
		 * Tests wheel value
		 * 
		 * @param id
		 *          the wheel Id
		 * @param value
		 *          the value to test
		 * @return true if wheel value is equal to passed value
		 */
		private int getWheelValue(int id)
			{
				return getWheel(id).getCurrentItem();
			}
	}
