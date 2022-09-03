## NP-bot Telegram bot for java

### Warning:
- You can use this bot if only you have an API key from Nova Poshta https://developers.novaposhta.ua/

### Created using:
- Maven
- java 11
- telegram bot library for java(https://github.com/rubenlagus/TelegramBots)

### Materials used to create the bot:
- article on the Dou https://dou.ua/forums/topic/38358/?from=fptech (in Ukrainian)
- github https://github.com/tarasvladyka/volunteer-telegram-bot

### Bot functionality:
- With the help of the bot you can get information about the parcel sent by Nova Poshta
  by indicating the phone number, name of the recipient or the parcel number.
  
### Run:
1. Create bot via BotFather (`t.me/BotFather`)
2. You can specify your `bot-token`, `bot-name` and `np-api-key` in the system environment variables or "hardcode" them in the org/mishchuk7/constants/Constants,
or create `application.yml` and specify them there.
3. Run `Application.main()`
4. Now navigate to your bot and try to send some sender or recipient phone number, name of the recipient or the parcel number.
