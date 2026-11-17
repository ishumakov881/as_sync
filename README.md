# Android Studio settings sync (`as_sync`)

Shared IDE config (templates, external tools, etc.) for Android Studio.

Machine-local **Path Variables** live in `options/path.macros.xml` and are **gitignored** — each OS sets its own values.

## Setup Path Variables

**Settings → Appearance & Behavior → Path Variables**

Or create/edit `options/path.macros.xml` next to this repo (same folder as `.git`).

External tools use: `$ADB$`, `$GIT$`, `$BASH$`, `$KEYTOOL$`.

---

### macOS — full `options/path.macros.xml`

Adjust paths if your SDK / Studio / Git differ.

```xml
<application>
  <component name="PathMacrosImpl">
    <macro name="ADB" value="/Users/USER/Library/Android/sdk/platform-tools/adb" />
    <macro name="GIT" value="/usr/bin/git" />
    <macro name="BASH" value="/bin/bash" />
    <macro name="KEYTOOL" value="/Users/USER/Applications/Android Studio.app/Contents/jbr/Contents/Home/bin/keytool" />
  </component>
</application>
```

Example (this machine):

```xml
<application>
  <component name="PathMacrosImpl">
    <macro name="ADB" value="/Users/admin/Library/Android/sdk/platform-tools/adb" />
    <macro name="GIT" value="/usr/bin/git" />
    <macro name="BASH" value="/bin/bash" />
    <macro name="KEYTOOL" value="/Users/admin/Applications/Android Studio.app/Contents/jbr/Contents/Home/bin/keytool" />
  </component>
</application>
```

If Studio is in `/Applications`:

`/Applications/Android Studio.app/Contents/jbr/Contents/Home/bin/keytool`

---

### Windows — full `options/path.macros.xml`

Adjust paths if your SDK / Studio / Git differ.

```xml
<application>
  <component name="PathMacrosImpl">
    <macro name="ADB" value="C:\android\sdk\platform-tools\adb.exe" />
    <macro name="GIT" value="C:\Program Files\Git\cmd\git.exe" />
    <macro name="BASH" value="C:\Program Files\Git\bin\bash.exe" />
    <macro name="KEYTOOL" value="C:\Program Files\Android\Android Studio\jbr\bin\keytool.exe" />
  </component>
</application>
```

Notes (Windows):

- `ADB` — or `%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe`
- `BASH` / `GIT` — from Git for Windows (needed for **Git add and commit**)
- `KEYTOOL` — newer Studio uses `jbr\bin\keytool.exe`; older may use `jre\bin\keytool.exe`

---

## Quick check

After setting variables, restart Android Studio (or reopen tools):

- **Tools → ADB → devices**
- **Tools → Git_ → Git add and commit**
- **Tools → External Tools → Hash Key …** (needs a valid keystore working dir)
