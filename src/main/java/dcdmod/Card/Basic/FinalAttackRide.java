package dcdmod.Card.Basic;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.RemoveKamenRideAction;
import dcdmod.Actions.RyukiAttackAction;
import dcdmod.Actions.TurnTimer;
import dcdmod.Card.Status.Kabuto_Photon;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Patches.HibikiTaikoKeyEvent;
import dcdmod.Power.KamenRideDecadePower;
import dcdmod.Vfx.*;


public class FinalAttackRide extends AbstractCustomCardWithType{
	
	public static final String ID = "FinalAttackRide";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/FinalAttackRide.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 11;
	private static boolean Kick = true;
	private int FARDCD = 0;
	private int t = 0;
	private int s = 0;
	private int FARnumber = 0;
	private List<TooltipInfo> tips;
	
	
	public FinalAttackRide() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[30], EXTENDED_DESCRIPTION[31]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		TurnTimer.StopBGM(false);
		if(!p.hasPower("KamenRideKabutoPower")){
			CardCrawlGame.sound.playA("FAR", 0F);
		}
		switch(FARnumber) {
			case 0:
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				break;
			case 1:
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_SoundsAndAnimation(p.drawX, p.drawY), 3.2F));
				if(!DCDmod.AnimationTrigger){
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_Background(false,false)));
				}
				if(p.hasPower("KuugaDragonPower")||p.hasPower("RisingDragonPower")){
					if(m.hasPower("KuugaSpecialPower")){
						if(DCDmod.AnimationTrigger){
							for(int i = 0;i < (m.getPower("KuugaSpecialPower").amount/3); i++) {
								AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
							}
							AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
						}
						else{
							AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Dragon_FAR(p,m,this.damage,m.getPower("KuugaSpecialPower").amount/3), 0F));
						}
					}
				}
				else if(p.hasPower("KuugaTitanPower")||p.hasPower("RisingTitanPower")){
					if(!DCDmod.AnimationTrigger){
						AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Titan_FAR(p,m,this.damage)));
					}
					else{
						if(p.hasPower("RisingMightyPower")) {
							AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage , DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
						}
						AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage , DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
						AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
					}
				}
				else if(p.hasPower("KuugaPegasusPower")||p.hasPower("RisingPegasusPower")) {
					if(!DCDmod.AnimationTrigger){
						if(p.hasPower("KuugaPegasusPower")){
							AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Pegasus_FAR(p,m,this.damage)));
						}
						else{
							AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Pegasus_FAR2(p,m,this.damage)));
						}
					}
					else{
						int x = 2;
						if(p.hasPower("RisingPegasusPower")) {
							x += 1;
						}
						for(int i = 0;i < x; i++) {
							for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
								if ((!monster.isDead) && (!monster.isDying)) {
									AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
								}
							}
						}
					}

				}
				else if(p.hasPower("RisingMightyPower")){
					if(m.hasPower("KuugaSpecialPower") && m.getPower("KuugaSpecialPower").amount >= 3){
						this.damage = this.damage*2;
					}
					if(!DCDmod.AnimationTrigger){
						AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_FAR_R_kick(p,m,this.damage)));
					}
					else{
						AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
						AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
						AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
					}
				}
				else {
					if(m.hasPower("KuugaSpecialPower") && m.getPower("KuugaSpecialPower").amount >= 3){
						this.damage = (int) (this.damage*1.5);
					}
					if(!DCDmod.AnimationTrigger){
						AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_FAR_kick(p,m,this.damage)));
					}
					else{
						AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
						AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
						AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
					}
				}
				break;
			case 2:
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_FAR_sounds(p.drawX - 200.00f, p.drawY + 250.00f), 4F));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				if(AbstractDungeon.player.hasPower("AgitoFlamePower") && AbstractDungeon.player.hasPower("AgitoStormPower")){
					CardCrawlGame.sound.playA("driversounds", 0.0f);
					CardCrawlGame.sound.playA("test1", 0.0f);//马赛克声音
					AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(p, p));
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KamenRideDecadePower(p,1),1));
				}
				else if(p.hasPower("SpecialStormPower")&&p.getPower("SpecialStormPower").amount>=3) {
					int x =p.getPower("SpecialStormPower").amount/3;
					for(int i = 0; i < x; i++ ) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
					}
				}
				break;
			case 3:
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_FAR_sounds(p.drawX - 200.00f, p.drawY + 250.00f), 4F));
				if(p.hasPower("DragClawPower") && p.hasPower("DragrederPower")) {
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "DragClawPower"));
					AbstractDungeon.actionManager.addToBottom(new RyukiAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 10));
				}
				else {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage , DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
				break;
			case 4:
				if(EnterButtonAction.AxelForm) {
					EnterButtonAction.PointerPoint +=1;
					if(EnterButtonAction.PointerPoint == 1) {
						AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_Timer(this.damage), 0F));
					}
				}
				else {
					CardCrawlGame.sound.playA("faiz_BGM2", 0.0f);
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_CrimsonSmash(m.drawX, m.drawY,m,this.damage), 0F));
					if(!DCDmod.AnimationTrigger) {
						AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 4.425F));
					}
					else {
						AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 2.0F));
					}
				}
				break;
			case 5:
				if(this.name.equals(EXTENDED_DESCRIPTION[37]) || this.name.equals(EXTENDED_DESCRIPTION[39])) {
					AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
			        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));  
			        if(this.name.equals(EXTENDED_DESCRIPTION[39])) {
			        	AbstractDungeon.actionManager.addToTop(new VFXAction(new Blade_FAR_sounds1(p.drawX - 200.00f, p.drawY + 250.00f), 4F));
			    		for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
			   			 	if ((!monster.isDead) && (!monster.isDying)) {
					    		AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));
					    		AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(monster.drawX, monster.drawY), 0.05F));
			   			 	}
			    		}
			        }
			        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
				}
				else if(this.name.equals(EXTENDED_DESCRIPTION[41])) {
			        for(int i = 0;i < 5; i++) {
			        	AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.SLASH_DIAGONAL));
			        }
				}
				else if(this.name.equals(EXTENDED_DESCRIPTION[22]) || this.name.equals(EXTENDED_DESCRIPTION[43]) || this.name.equals(EXTENDED_DESCRIPTION[45])) {
			        if(this.name.equals(EXTENDED_DESCRIPTION[43]) || this.name.equals(EXTENDED_DESCRIPTION[45])) {
			        	if(this.name.equals(EXTENDED_DESCRIPTION[43])) {
			        		AbstractDungeon.actionManager.addToTop(new VFXAction(new Blade_FAR_sounds2(p.drawX - 200.00f, p.drawY + 250.00f), 4F));
			        	}
			        	else {
			        		AbstractDungeon.actionManager.addToTop(new VFXAction(new Blade_FAR_sounds3(p.drawX - 200.00f, p.drawY + 250.00f), 4F));
			        	}
			    		AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));
			    		AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
			        }
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
				break;
			case 6:
				if(!p.hasPower("HibikiKurenaiPower")) {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_FAR_SoundsAndAnimation(this.damage,DamageType.NORMAL),0F));
				}
				break;
			case 7:
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_FAR_sounds(p.drawX - 200.00f, p.drawY + 250.00f), 4F));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				if(p.hasPower("KabutoDexterityPower")||p.hasPower("KabutoStrengthPower")){
					AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_Photon(), 1));
				}
				if(p.hasPower("KabutoDexterityPower")) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
				}
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_FAR_SoundsAndAnimation(p.drawX, p.drawY), 4F));
				if(!DCDmod.AnimationTrigger){
					AbstractDungeon.actionManager.addToBottom(new VFXAction(new Decade_FAR_kick(p, m,this.damage + FARDCD), 0F));
				}
				else{
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage + FARDCD, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage + FARDCD, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
				break;
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(this.name.equals(EXTENDED_DESCRIPTION[45]) &&AbstractDungeon.player.hasPower("Dexterity") && AbstractDungeon.player.getPower("Dexterity").amount>0) {
			this.damage += AbstractDungeon.player.getPower("Dexterity").amount;
			this.isDamageModified = true;
		}
		if(AbstractDungeon.player.hasPower("BladeKickPower")&&Kick) {
			this.damage += this.damage;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new FinalAttackRide();
    }

	@Override
	public List<TooltipInfo> getCustomTooltips() {
		if(AbstractDungeon.player instanceof Decade && AbstractDungeon.player.hasPower("KamenRideRyukiPower")) {return this.tips;}
		return null;
	}
	
	@Override
	public void optionDecade() {
		FARnumber = 10;
		loadCardImage("img/cards/FinalAttackRide_Decade.png");
		Kick = true;
		this.target = CardTarget.ENEMY;
		this.baseDamage = 11;
		this.rawDescription = EXTENDED_DESCRIPTION[0] + EXTENDED_DESCRIPTION[2] + FARDCD + EXTENDED_DESCRIPTION[3] +  EXTENDED_DESCRIPTION[1];
		this.name = EXTENDED_DESCRIPTION[21];
		initializeDescription();
	}

	@Override
	public void optionKuuga() {
		FARnumber = 1;
		this.target = CardTarget.ENEMY;
		loadCardImage("img/cards/FinalAttackRide_Kuuga.png");
		if(AbstractDungeon.player.hasPower("KuugaDragonPower")){
			Kick = false;
			this.rawDescription = EXTENDED_DESCRIPTION[4];
			this.baseDamage = 6;
			this.name = EXTENDED_DESCRIPTION[8];
		}
		else if(AbstractDungeon.player.hasPower("KuugaTitanPower")) {
			Kick = false;
			this.rawDescription = EXTENDED_DESCRIPTION[7];
			this.baseDamage = 11 + AbstractDungeon.player.getPower("KuugaTitanPower").amount;
			this.name = EXTENDED_DESCRIPTION[9];
		}
		else if(AbstractDungeon.player.hasPower("KuugaPegasusPower")) {
			Kick = false;
			this.baseDamage = 11;
			this.rawDescription = EXTENDED_DESCRIPTION[27];
			this.name = EXTENDED_DESCRIPTION[11];
		}
		else if(AbstractDungeon.player.hasPower("RisingDragonPower")) {
			Kick = false;
			this.rawDescription = EXTENDED_DESCRIPTION[4];
			this.baseDamage = 6;
			this.name = EXTENDED_DESCRIPTION[15];
		}
		else if(AbstractDungeon.player.hasPower("RisingTitanPower")) {
			Kick = false;
			this.rawDescription = EXTENDED_DESCRIPTION[18];
			this.baseDamage = 11 + AbstractDungeon.player.getPower("RisingTitanPower").amount + AbstractDungeon.player.currentBlock;
			this.name = EXTENDED_DESCRIPTION[12];
			if(AbstractDungeon.player.hasPower("RisingMightyPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[53];
				this.name = EXTENDED_DESCRIPTION[52];
			}
		}
		else if(AbstractDungeon.player.hasPower("RisingPegasusPower")) {
			Kick = false;
			this.rawDescription = EXTENDED_DESCRIPTION[16];
			this.name = EXTENDED_DESCRIPTION[14];
		}
		else if(AbstractDungeon.player.hasPower("RisingMightyPower")) {
			Kick = true;
			this.rawDescription = EXTENDED_DESCRIPTION[17];
			this.name = EXTENDED_DESCRIPTION[13];
		}
		else{
			Kick = true;
			this.baseDamage = 11;
			this.name = EXTENDED_DESCRIPTION[10];
			this.rawDescription = EXTENDED_DESCRIPTION[6];
		}
		initializeDescription();
	}

	@Override
	public void optionAgito() {
		FARnumber = 2;
		loadCardImage("img/cards/FinalAttackRide_Agito.png");
		this.baseDamage = 11;
		this.target = CardTarget.ENEMY;
		if(AbstractDungeon.player.hasPower("AgitoStormPower") && !AbstractDungeon.player.hasPower("AgitoFlamePower")){
			Kick = false;
			this.rawDescription = EXTENDED_DESCRIPTION[20];
			this.name = EXTENDED_DESCRIPTION[19];
		}
		else if(AbstractDungeon.player.hasPower("AgitoFlamePower") && !AbstractDungeon.player.hasPower("AgitoStormPower")){
			Kick = false;
			this.rawDescription = EXTENDED_DESCRIPTION[24];
			this.name = EXTENDED_DESCRIPTION[23];
		}
		else if(AbstractDungeon.player.hasPower("AgitoFlamePower") && AbstractDungeon.player.hasPower("AgitoStormPower")) {
			Kick = true;
			this.rawDescription = EXTENDED_DESCRIPTION[26];
			this.name = EXTENDED_DESCRIPTION[25];
		}
		else {
			this.rawDescription = EXTENDED_DESCRIPTION[24];
			this.name = EXTENDED_DESCRIPTION[22];
		}
		initializeDescription();
	}

	@Override
	public void optionRyuki() {
		FARnumber = 3;
		loadCardImage("img/cards/FinalAttackRide_Ryuki.png");
		Kick = true;
		this.baseDamage = 20;
		this.target = CardTarget.ENEMY;
		if(AbstractDungeon.player.hasPower("DragClawPower")) {
			int x = AbstractDungeon.player.getPower("DragClawPower").amount * 6;
			this.baseDamage += x;
		}
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			this.baseDamage += 10;
		}
		this.rawDescription = EXTENDED_DESCRIPTION[29];
		this.name = EXTENDED_DESCRIPTION[28];
		initializeDescription();
	}

	@Override
	public void optionFaiz() {
		FARnumber = 4;
		loadCardImage("img/cards/FinalAttackRide_Faiz.png");
		Kick = true;
		this.baseDamage = 1;
		this.target = CardTarget.ENEMY;
		if(EnterButtonAction.AxelForm) {
			this.rawDescription = EXTENDED_DESCRIPTION[34];
			this.name = EXTENDED_DESCRIPTION[35];
		}
		else {	
			this.rawDescription = EXTENDED_DESCRIPTION[33];
			this.name = EXTENDED_DESCRIPTION[32];
		}
		initializeDescription();
	}

	@Override
	public void optionBlade() {
		FARnumber = 5;
		loadCardImage("img/cards/FinalAttackRide_Blade.png");
		if(!AbstractDungeon.player.hasPower("BladeSlashPower")&&!AbstractDungeon.player.hasPower("BladeKickPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[36];
		}
		//骑士剑
		else if(AbstractDungeon.player.hasPower("BladeSlashPower")&&!AbstractDungeon.player.hasPower("BladeKickPower")
				&&!AbstractDungeon.player.hasPower("BladeThunderPower")&&!AbstractDungeon.player.hasPower("BladeMachPower")) {
			Kick = false;
			this.baseDamage = 15;
			this.target = CardTarget.ALL_ENEMY;
			this.rawDescription = EXTENDED_DESCRIPTION[38];
			this.name = EXTENDED_DESCRIPTION[37];
		}
		//骑士踢
		else if(!AbstractDungeon.player.hasPower("BladeSlashPower")&&AbstractDungeon.player.hasPower("BladeKickPower")
				&&!AbstractDungeon.player.hasPower("BladeThunderPower")&&!AbstractDungeon.player.hasPower("BladeMachPower")) {
			Kick = true;
			this.baseDamage = 10;
			this.target = CardTarget.ENEMY;
			this.rawDescription = EXTENDED_DESCRIPTION[47];
			this.name = EXTENDED_DESCRIPTION[22];
		}
		//闪电裂斩
		else if(AbstractDungeon.player.hasPower("BladeSlashPower")&&!AbstractDungeon.player.hasPower("BladeKickPower")
				&&AbstractDungeon.player.hasPower("BladeThunderPower")&&!AbstractDungeon.player.hasPower("BladeMachPower")) {
			Kick = false;
			this.baseDamage = 20 + 6 * t;
			this.target = CardTarget.ALL_ENEMY;
			this.rawDescription = EXTENDED_DESCRIPTION[40];
			this.name = EXTENDED_DESCRIPTION[39];
		}
		//音速裂斩
		else if(AbstractDungeon.player.hasPower("BladeSlashPower")&&!AbstractDungeon.player.hasPower("BladeKickPower")
				&&!AbstractDungeon.player.hasPower("BladeThunderPower")&&AbstractDungeon.player.hasPower("BladeMachPower")) {
			Kick = false;
			this.baseDamage = 3 + s;
			this.target = CardTarget.ALL_ENEMY;
			this.rawDescription = EXTENDED_DESCRIPTION[42];
			this.name = EXTENDED_DESCRIPTION[41];
		}
		//闪电爆破
		else if(!AbstractDungeon.player.hasPower("BladeSlashPower")&&AbstractDungeon.player.hasPower("BladeKickPower")
				&&AbstractDungeon.player.hasPower("BladeThunderPower")&&!AbstractDungeon.player.hasPower("BladeMachPower")) {
			Kick = true;
			this.baseDamage = 15 + 3 * t;
			this.target = CardTarget.ENEMY;
			this.rawDescription = EXTENDED_DESCRIPTION[44];
			this.name = EXTENDED_DESCRIPTION[43];
		}
		//闪电音速
		else if(!AbstractDungeon.player.hasPower("BladeSlashPower")&&AbstractDungeon.player.hasPower("BladeKickPower")
				&&AbstractDungeon.player.hasPower("BladeThunderPower")&&AbstractDungeon.player.hasPower("BladeMachPower")) {
			Kick = true;
			this.baseDamage = 20 + 4 * t;
			this.target = CardTarget.ENEMY;
			this.rawDescription = EXTENDED_DESCRIPTION[46];
			this.name = EXTENDED_DESCRIPTION[45];
		}
		initializeDescription();
	}

	@Override
	public void optionHibiki() {
		FARnumber = 6;
		loadCardImage("img/cards/FinalAttackRide_Hibiki.png");
		Kick = false;
		this.baseDamage = HibikiTaikoKeyEvent.ComboPoint/3;
		this.target = CardTarget.ALL_ENEMY;
		this.rawDescription = EXTENDED_DESCRIPTION[49];
		this.name = EXTENDED_DESCRIPTION[48];
		initializeDescription();
	}

	@Override
	public void optionKabuto() {
		FARnumber = 7;
		Kick = true;
		loadCardImage("img/cards/FinalAttackRide_Kabuto.png");
		this.baseDamage = 10;
		this.target = CardTarget.ENEMY;
		this.rawDescription = EXTENDED_DESCRIPTION[51];
		this.name = EXTENDED_DESCRIPTION[22];
		initializeDescription();
	}

	@Override
	public void optionDenO() {
		FARnumber = 8;
	}

	@Override
	public void optionKiva() {
		FARnumber = 9;
	}

	@Override
	public void optionNeutral() {
		FARnumber = 0;
		loadCardImage("img/cards/FinalAttackRide.png");
		Kick = true;
		this.target = CardTarget.ENEMY;
		this.baseDamage = 11;
		this.rawDescription = DESCRIPTION;
		this.name = NAME;
		initializeDescription();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.hasPower("KamenRideBladePower")&&(!p.hasPower("BladeSlashPower")&&!p.hasPower("BladeKickPower"))) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[36];
		}
		if(p.hasPower("KuugaDragonPower")||p.hasPower("RisingDragonPower")){
			if(m !=null && m.hasPower("KuugaSpecialPower")) {
				if( m.getPower("KuugaSpecialPower").amount<3) {
					canUse = false;
					this.cantUseMessage = EXTENDED_DESCRIPTION[5];
				}
			}
			else {
				canUse = false;
				this.cantUseMessage = EXTENDED_DESCRIPTION[5];
			}
		}
		if(p.hasPower("KamenRideHibikiPower") && !HibikiTaikoKeyEvent.Fever) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[50];
		}
		return canUse;
	}
	
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("FinalAttackRide");
        NAME = FinalAttackRide.cardStrings.NAME;
        DESCRIPTION = FinalAttackRide.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FinalAttackRide.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(AbstractDungeon.player != null) {
			//decade计数
			int count = 0;
			super.applyPowers();
			for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
				if (c.hasTag(DCDmod.RiderCard)) {
					count++;
					FARDCD = count;
				}
				if (!c.hasTag(DCDmod.RiderCard)) {
					count++;
					FARDCD = count;
				}
			}
			//blade计数
			if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
				t = AbstractDungeon.player.getPower("BladeThunderPower").amount;
			}
			if(AbstractDungeon.player.hasPower("BladeSlashPower")) {
				s = AbstractDungeon.player.getPower("BladeSlashPower").amount;
			}
		}
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.FAR[0], DCDmod.FAR_P[0]);
		}
		else if(this.costForTurn == -1) {
			setBannerTexture(DCDmod.FAR[6], DCDmod.FAR_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
		}
	}

	
	
}
