months = ['Jan',
        'Feb',
        'Mar',
        'Apr',
        'May',
        'Jun',
        'Jul',
        'Aug',
        'Sep',
        'Oct',
        'Nov',
        'Dec']
endings = ['st','nd','rd'] + 17*['th']\
        + ['st','nd','rd'] + 7*['th']\
        + ['st']
year   = raw_input('Years:')
month   = raw_input('Month(1-12):')
day     = raw_input('Day(1-31):')

month_number = int (month)
day_number = int (day)

month_name = months[month_number-1]
ordinal = day + endings[day_number-1]
print month_name + ' ' + ordinal + ',' + year

raw_input("Press<enter>")