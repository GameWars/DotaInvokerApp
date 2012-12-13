package com.example.dota.invoker.trainer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	
	private List<Spell> spells = new ArrayList<Spell>();
	private String invoke = "";
	private Spell answer;
	private Spell last_spell = null;
	private  MediaPlayer media;
	private Random rnd = new Random();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        randomSpell(spells.size());
    }

    
    private void randomSpell(int max_rnd) {
    	int random = rnd.nextInt(max_rnd);
    	Spell spell = spells.get(random);
    	for (int x = 0; x <= random; x++) {
    	      spell = spells.get(x);
    	}
    	answer = spell;
    	if (last_spell != null)  {
    		spells.add(last_spell);
    	}
    	last_spell = answer;
    	ImageView iv = (ImageView) findViewById(R.id.imageView1);
    	iv.setImageResource(spell.getImg());
    }
    
	private void init() {
		spells.add(new Spell("Cold Snap", getResources().getIdentifier("cold_snap", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("QQQ")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_coldsnap_01))));
		spells.add(new Spell("Ghost Walk", getResources().getIdentifier("ghost_walk", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("QQW", "QWQ", "WQQ")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_ghostwalk_02))));
		spells.add(new Spell("Ice Wall", getResources().getIdentifier("ice_wall", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("QQE", "QEQ", "EQQ")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_icewall_01))));
		spells.add(new Spell("EMP", getResources().getIdentifier("emp", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("WWW")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_emp_01))));
		spells.add(new Spell("Tornado", getResources().getIdentifier("tornado", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("WWQ", "WQW", "QWW")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_tornado_05))));
		spells.add(new Spell("Alacrity", getResources().getIdentifier("alacrity", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("WWE", "WEW", "EWW")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_alacrity_01, 1))));
		spells.add(new Spell("Sun Strike", getResources().getIdentifier("sun_strike", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("EEE", "EEE", "EEE")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_sunstrike_03))));
		spells.add(new Spell("Forge Spirit", getResources().getIdentifier("forge_spirit", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("QEE", "EQE", "EEQ")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_forgespirit_03))));
		spells.add(new Spell("Chaos Meteor", getResources().getIdentifier("chaos_meteor", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("WEE", "EWE", "EEW")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_chaosmeteor_01))));
		spells.add(new Spell("Deafening Blast", getResources().getIdentifier("deafening_blast", "drawable", getPackageName()), new ArrayList<String>(Arrays.asList("QWE", "QEW", "EQW", "EWQ", "WQE", "WEQ")), new ArrayList<Integer>(Arrays.asList(R.raw.invo_ability_deafeningblast_02))));
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClick(View view) {
    	// remove first char when more than 3 chars
    	if (invoke.length() == 3)
    		invoke = invoke.substring(1);
    	// add char to current invoke
    	switch (view.getId()) {
    		case R.id.imageButton1:
        		invoke += "Q";
        		break;
        	case R.id.imageButton2:
        		invoke += "W";
        		break;
        	case R.id.imageButton3:
        		invoke += "E";
        		break;
    	}
    }

	public void onInvoke(View view) {
		TextView tv = (TextView) findViewById(R.id.text1);
		Iterator<Spell> itr_spell = spells.iterator();
		boolean spell_found = false;
		while (itr_spell.hasNext()) {
			Spell spell = itr_spell.next();
			Iterator<String> itr_shortcuts = spell.getShortcuts().iterator();
			while (itr_shortcuts.hasNext()) {
				if (invoke.equals(itr_shortcuts.next()) && spell.equals(answer)) {
					// add sound
					spell_found = true;
					media = MediaPlayer.create(getBaseContext(), spell.getSounds().get(0));
					media.start();
					tv.setTextColor(Color.GREEN);
					tv.setText("Success !");
					break;
				}	
			}
		}
		if (spell_found  == false) {
			media = MediaPlayer.create(getBaseContext(), R.raw.invo_anger_04);
			media.start();
			tv.setTextColor(Color.RED);
			tv.setText("Your Invoke :" + invoke + "\nAnswer:" + answer.getShortcuts().get(0));
			// add sound
		}
		spells.remove(last_spell);
		invoke = "";
		randomSpell(spells.size());	
	}
    
}
