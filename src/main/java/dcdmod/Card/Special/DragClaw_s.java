package dcdmod.Card.Special;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.DragClawPower;
import dcdmod.Vfx.DragClaw_sounds;
import dcdmod.Vfx.Ryuki_guardstrike;
import dcdmod.Vfx.Ryuki_strike;



public class DragClaw_s extends AbstractCustomCardWithType{
	
	public static final String ID = "DragClaw";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/DragClaw.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 10;
	private List<TooltipInfo> tips;
	
	
	public DragClaw_s() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Ryuki);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[6], EXTENDED_DESCRIPTION[7]));
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		int x = 1;
		int d = this.damage;
		CardCrawlGame.sound.playA("attackride", 0.0f);
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
		for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
			 if ((!monster.isDead) && (!monster.isDying)&& monster != m) {
				 AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(p, this.damage/2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
				 d+=(this.damage-5);
				 x++;
			 }
		}
		if(p.hasPower("MirrorWorldPower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragClawPower(p, x*2), x*2));
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragClawPower(p, x), x));
		}
		if(p.hasPower("DragShieldPower")&&p.getPower("DragShieldPower").amount>=2) {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "DragShieldPower", 2));
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, d));
			if(p.hasPower("KamenRideRyukiPower")) {
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_guardstrike(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 1.25F));
			}
		}
		else {
			if(p.hasPower("KamenRideRyukiPower")) {
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_strike(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 1F));
			}
		}
		AbstractDungeon.actionManager.addToTop(new VFXAction(new DragClaw_sounds(p.drawX - 200.00f, p.drawY + 250.00f), 1.5F));
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			this.damage += 5;
			this.isDamageModified = true;
		}
		if(AbstractDungeon.player.hasPower("KamenRideRyukiPower")) {
			this.damage += 3;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new DragClaw_s();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
	public void optionDecade() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionKuuga() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionAgito() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionRyuki() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionFaiz() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionBlade() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionHibiki() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionKabuto() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionDenO() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionKiva() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}

	@Override
	public void optionNeutral() {
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			if(AbstractDungeon.player.hasPower("DragShieldPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[0];
			}
		}
		else {
			if(AbstractDungeon.player.hasPower("DragShieldPower")&&AbstractDungeon.player.getPower("DragShieldPower").amount>=2){
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[0];
			}
			else {
				this.rawDescription = EXTENDED_DESCRIPTION[1]  + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[0];
			}
		}
		initializeDescription();
	}
    
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			this.upgradeDamage(3);
		}
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("DragClaw");
        NAME = DragClaw_s.cardStrings.NAME;
        DESCRIPTION = DragClaw_s.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DragClaw_s.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
