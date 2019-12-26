# Telegram Java Calculator

Basic Calculator made for calculating simple arithmetic operations.
The bot may be accessible on [the Telegram Web](https://t.me/jcalc_bot).

This java application works along with Telegram API.
The actions supported by calculator bot are adding, subtract, multiply, divide operations with no more than 2 numbers.

[![MIT license](https://badgen.net/badge/license/MIT/blue)](https://matveieff.mit-license.org)
[![Telegram bot link](https://badgen.net/badge/icon/telegram/blue?icon=telegram&label=bot)](https://t.me/jcalc_bot)
[![Travis](https://img.shields.io/travis/aubique/jcalc)](https://travis-ci.org/aubique/jcalc)

## Deployment
This bot uses webhooks to communicate with Telegram servers.
You'll want to install `ngrok` app that tunnels localhost dev environment.
It would provide an unique URL on the `ngrok.io` domain to forward incoming requests to your local development environment.

To start, head over to [Ngrok download page](https://ngrok.com/download) and grab the binary for your OS.

Once downloaded, start **Ngrok** using this command:
```
./ngrok http 8443 --region eu
```

Take a look at the `Forwarding` line to copy that Ngrok domain name to `config.xml` as `external` URL key.

Make sure your configuration file looks like this:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
    <comment>Telegram Channel Bot Configuration</comment>
    <entry key="username">jcalc_bot</entry>
    <entry key="token">TOKEN-PROVIDED-BY-BOTFATHER</entry>
    <entry key="internal">https://localhost:8443</entry>
    <entry key="external">https://eece1254.eu.ngrok.io</entry>
</properties>
```

Then pick the bot `token` and `external` URL to call the `setWebHook` method in the Telegram API:
```
https://api.telegram.org/bot{TOKEN}/setWebhook?url={HTTPS-NGROK-DOMAIN}
```

> To check if webhook is set up properly call this method `../bot{TOKEN}/getWebhookInfo`

## Built With

- [IntelliJ IDEA](https://www.jetbrains.com/idea) - JetBrains IDE meant for Java development
- [TelegramBots](https://github.com/rubenlagus/TelegramBots) - Java library to create bots using Telegram Bots API
- [Maven](https://maven.apache.org/) - Dependency Management

## Sources

- Developer.com: [Refactor Java switch Statements](https://www.developer.com/java/data/seven-ways-to-refactor-java-switch-statements.html)
- Dev.to: [Creating a Telegram Bot in Java](https://dev.to/codegym_cc/creating-a-telegram-bot-in-java-from-conception-to-deployment-3a8c)
- Github.com/[rubenlagus/TelegramBotsExample](https://github.com/rubenlagus/TelegramBotsExample)

## Todo

- [ ] Make tests with JUnit and Mockito
- [ ] Integrate build testing with Travis CI
- [x] Set environment variables with XML configuration
